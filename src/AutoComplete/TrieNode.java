package AutoComplete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrieNode {


    String prefix;
    int rank;
    boolean isWord;
    HashMap<String, TrieNode> childrens;
    List<String> suggesions;

    public TrieNode(String str){
          this.prefix = str;
          this.rank = 0;
          this.isWord = false;
          childrens = new HashMap<>();
          suggesions = new ArrayList<>();
    }


    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public HashMap<String, TrieNode> getChildrens() {
        return childrens;
    }

    public void setChildrens(HashMap<String, TrieNode> childrens) {
        this.childrens = childrens;
    }

    public List<String> getSuggesions() {
        return suggesions;
    }

    public void setSuggesions(List<String> suggesions) {
        this.suggesions = suggesions;
    }

}
