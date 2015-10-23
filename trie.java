import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Solution
{
    private static final String wordsCorpusFilePath = "words.txt";
    private static final String testInputFilePath = "examples3.txt";

    private final TrieNode trieRoot = new TrieNode();

    private void loadWordsAndBuildTrieDictionary()
	    throws IOException
    {
	try (BufferedReader br = new BufferedReader(new FileReader(wordsCorpusFilePath)))
	{
	    String word;
	    while ((word = br.readLine()) != null)
	    {
		word = word.trim().toLowerCase();
		trieRoot.addWord(word, 0);
		char c = word.charAt(word.length() - 1);
		//if (!Character.isDigit(c) && 's' != c)
		//{
		//    trieRoot.addWord(word + "s", 0);
		//}
	    }
	}
    }

    private int calculateStartIndex(String testExample)
    {
	if (testExample.startsWith("#"))
	{
	    return "#".length();
	}
	else if (testExample.startsWith("www."))
	{
	    return "www.".length();
	}
	else
	{
	    return 0;
	}
    }

    private void process(String testExample)
    {
	testExample = testExample.trim().toLowerCase();
	if (testExample.isEmpty())
	{
	    return;
	}
	List<TrieNode> resultWordNodes = new LinkedList<>();
	int startTextIndex = calculateStartIndex(testExample);
	//System.out.println(testExample);
	processRecursive(testExample, startTextIndex, trieRoot, resultWordNodes);
	//System.out.println("*****");
	if (!resultWordNodes.isEmpty())
	{
	    Iterator<TrieNode> iterator = resultWordNodes.iterator();
	    System.out.print(iterator.next().getWord());
	    while (iterator.hasNext())
	    {
		System.out.print(' ');
		System.out.print(iterator.next().getWord());
	    }
	}
	else
	{
	    throw new RuntimeException("not in dict:" + testExample);
	    //System.out.print(testExample.substring(startTextIndex, endTextIndex));
	}

	System.out.println();
	//System.out.println("*******************************************");
    }
    int endTextIndex = 0;

    private boolean processRecursive(String text, int currTextIndex, TrieNode prevNode, List<TrieNode> resultWordNodes)
    {
	//check if text is ending and if prevNode represents a valid word
	if (currTextIndex == text.length() || '.' == text.charAt(currTextIndex))
	{
	    endTextIndex = currTextIndex;
	    boolean success = prevNode.isWord;
	    if (success)
	    {
		if (prevNode != trieRoot)
		{
		    resultWordNodes.add(0, prevNode);
		}
		return true;
	    }
	    else //!success
	    {
		return false;
	    }
	}

	char currTextChar = text.charAt(currTextIndex);

	//extract digits sequence (for example 12.34) - START
	if (Character.isDigit(currTextChar))
	{
	    if (prevNode.isWord)
	    {
		StringBuilder digitStringBuilder = new StringBuilder("" + currTextChar);
		int endDigitsIndex = currTextIndex + 1;
		for (; endDigitsIndex < text.length(); ++endDigitsIndex)
		{
		    char c = text.charAt(endDigitsIndex);
		    if (Character.isDigit(c))
		    {
			digitStringBuilder.append(c);
		    }
		    else if ('.' == c)
		    {
			if (endDigitsIndex + 1 < text.length() && Character.isDigit(text.charAt(endDigitsIndex + 1)))
			{
			    digitStringBuilder.append(c);
			}
			else
			{
			    break;
			}
		    }
		    else
		    {
			break;
		    }
		}

		TrieNode digitsTrieNode = new TrieNode(trieRoot, digitStringBuilder.toString()); //TODO add static digit trie nodes and one for decimal dot
		digitsTrieNode.isWord = true;
		boolean success = processRecursive(text, endDigitsIndex, trieRoot, resultWordNodes);
		if (success)
		{
		    resultWordNodes.add(0, digitsTrieNode);
		    if (prevNode != trieRoot)
		    {
			resultWordNodes.add(0, prevNode);
		    }
		    return true;
		}
		else //!success
		{
		    return false;
		}
	    }
	    else
	    {
		return false;
	    }
	} //extract digits sequence (for example 12.34) - END

	//main body - checking various cases
	TrieNode currNode = prevNode.getChild(currTextChar);
	if (currNode == null)
	{
	    if (prevNode.isWord)
	    {
		boolean success = processRecursive(text, currTextIndex, trieRoot, resultWordNodes);
		if (success)
		{
		    resultWordNodes.add(0, prevNode);
		    return true;
		}
		else //!success
		{
		    return false;
		}
	    }
	    else
	    {
		//???
		return false;
	    }
	}
	else //currNode != null
	{
	    boolean success = processRecursive(text, currTextIndex + 1, currNode, resultWordNodes);
	    if (success)
	    {
		//wordTrieNodes.add(currNode);
		return true;
	    }
	    else //nie udalo sie powiekszyc slowa aby bylo w slowniku
	    {
		if (currNode.isWord)
		{
		    boolean success2 = processRecursive(text, currTextIndex + 1, trieRoot, resultWordNodes);
		    if (success2)
		    {
			resultWordNodes.add(0, currNode);
			return true;
		    }
		    else //!success
		    {
			return false;
		    }
		}
		else
		{
		    return false;
		}
	    }
	}
    }

    public static void main(String[] args)
	    throws IOException
    {
	Solution s = new Solution();
	s.loadWordsAndBuildTrieDictionary();
	Scanner in = new Scanner(System.in);
	//Scanner in = new Scanner(new File(testInputFilePath));
	int numberOfTestCases = Integer.parseInt(in.nextLine());
	for (int i = 0; i < numberOfTestCases; ++i)
	{
	    s.process(in.nextLine());
	}
    }

    private static class TrieNode
    {
	private final TrieNode parent;
	private final Map<Character, TrieNode> children = new HashMap<>();
	private boolean isWord;
	private final String character;

	public TrieNode(TrieNode parent, String character)
	{
	    this.parent = parent;
	    this.character = character;
	}

	public TrieNode()
	{
	    this(null, "^");
	    isWord = true;
	}

	private void addWord(String word, int currIndex)
	{
	    char firstWordChar = word.charAt(currIndex);
	    TrieNode child = children.get(firstWordChar);
	    if (child == null)
	    {
		child = new TrieNode(this, "" + firstWordChar);
		children.put(firstWordChar, child);
	    }

	    if (currIndex == word.length() - 1)
	    {
		child.isWord = true;
	    }

	    if (currIndex + 1 < word.length())
	    {
		child.addWord(word, currIndex + 1);
	    }
	}

	public TrieNode getChild(char character)
	{
	    if ('0' <= character && character <= '9')
	    {
		return null;
	    }
	    return children.get(character);
	}

	@Override
	public String toString()
	{
	    return "TrieNode{ isWord=" + isWord + ", character=" + character + ", word=" + getWord() + " }";
	}

	public String getWord()
	{
	    return (parent == null) ? "" : (parent.getWord() + character);
	}
    }
}

