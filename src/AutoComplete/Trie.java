package AutoComplete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {

    private TrieNode root;

    private HashMap <String,List<String>> suggesionHash = new HashMap<>();
    public Trie()
    {
    }
    public static void main(String[] args) {
        System.out.println("trie printing");
        Trie trie = new Trie();
        TrieNode nodeRoot = trie.addItems("ABCD");
        trie.addItems("ABCDE");
        trie.addItems("ABACD");
        trie.addItems("ABCAD");
        trie.addItems("AACD");
        trie.addItems("AMCD");
        trie.addItems("ABMCD");
        trie.addItems("ABNCD");
        trie.addItems("ABLCD");

        trie.printAllWords(nodeRoot);
        trie.findSuggesion("AB");
    }

    private TrieNode addItems(String param){
        if(this.root ==null){
            root = new TrieNode(" ");
        }
        insertItemInTrie(root,param);
        return root;
    }

    public void findSuggesion(String key) {
        if(suggesionHash.isEmpty())
        {
            System.out.println("No suggesion for the string - "+ key);
            return;
        }
        if(suggesionHash.containsKey(key) && !suggesionHash.get(key).isEmpty()) {
            System.out.println("Suggesions for " +" key :- \n"  + key);
            for(String value: suggesionHash.get(key)){
                System.out.println(" "+ value);
            }
        }
    }
    private void insertItemInTrie(TrieNode root, String param)
    {
        TrieNode node = root;
        List<TrieNode> ancestors = new ArrayList<>();
        for (int i=0; i<param.length();i++){
            ancestors.add(node);
            String prefix = param.substring(0,i+1).intern();
            if(node.getChildrens().containsKey(prefix)) {
                node = node.getChildrens().get(prefix);
            }
            else {
                TrieNode newNode = new TrieNode(prefix);
                node.getChildrens().put(prefix,newNode);
                node = newNode;
            }
        }

        ancestors.add(node);
        System.out.println(param +" - Added \n");
        node.setRank(node.getRank()+1);
        node.setWord(true);
        updateSugessionList(ancestors,param);
    }

    public void updateSugessionList(List<TrieNode> ancestors, String param)
    {
        for(TrieNode node: ancestors){
            node.getSuggesions().add(param);
            if(!suggesionHash.containsKey(node.getPrefix()))
            {
                List<String> list = new ArrayList<>();
                suggesionHash.put(node.getPrefix(),list);
            }
            suggesionHash.get(node.getPrefix()).add(param);
        }
    }
    public void printAllWords(TrieNode cur) {
        if (cur.isWord)
            System.out.println(cur.getPrefix() + "\n" );
        for (String key : cur.getChildrens().keySet())
            printAllWords(cur.getChildrens().get(key));
    }

}
