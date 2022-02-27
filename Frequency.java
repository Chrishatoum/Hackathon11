// This code takes as input an array of strings, that represent 
// the discussions on a forum and then sorts by frequency if a word was said 
// by all the users.The user is defined as the first element in the string and then seperated 
// by a space from the other words. If two words have the same frequency, they are ordered alphabetically

// This code can be used to maximize efficiency in discussion searches for staff of the forum, to 
// look up for "hot" words
import java.util.*;
public class Frequency {
		public static void main(String args[]) {
			String[] posts = new String[] {"Chris Let's code something" , "Emma Let's code somthing else" , "Jack Let's not code" , "Marc Let's we code code code !"};

	 	Discussion_Board(posts);
	 }


	public static void Discussion_Board(String[] posts){

// Intitialize variables and data structures 
		TreeMap<String, List<String> > wordCount = new TreeMap<String, List<String>>();
		TreeMap<String, Integer>  tree= new TreeMap<String, Integer>();
		ArrayList<String> newWords = new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();
		String user = "";

		// Get the users and store them in "users", get the words and store them in the hashmap 
		List<String> users = new ArrayList<String>();
		for (String post: posts) {
			ArrayList<String> individualWords = new ArrayList<String>(Arrays.asList(post.split(" ")));
			user = individualWords.remove(0);
			users.add(user);
			for (String word: individualWords) {
				
				// if the word is already in the hashmap
				if (wordCount.containsKey(word)) {

					List<String> w = wordCount.get(word);
					w.add(user);
					wordCount.put(word, w);
				}
				else {
					newWords.add(word);
					wordCount.put(word, new ArrayList(Arrays.asList(user)));
				}
			}
		}
		
		// add the words with all the number of users in a treemap, if the word was used by all the users
		for(int i =0 ; i< wordCount.size(); i++){

			if(wordCount.get(newWords.get(i)).containsAll(users)) {
				tree.put(newWords.get(i), wordCount.get(newWords.get(i)).size());

			}
		}
		// sort the treemap in ascending order and add them to our result arraylist
		Map finaltree = sortTree(tree);
		Set<String> keys = finaltree.keySet();
		for(String temp : keys){
			result.add(temp);
		}
// reverse the results and then go through them and swap if 2 have the same frequency
		Collections.reverse(result);
		for(int i=0 ; i< result.size()-2;i++) {
			String word1 = result.get(i);
			String word2 = result.get(i+1);
			//	if (wordCount.get(word1).size() > wordCount.get(word1+1).size())  word1;

			if(wordCount.get(word1).size() == wordCount.get(word2).size()) {
				if(word1.compareTo(word2) > 0) {
					Collections.swap(result, i, i+1);
				}
			}
		}


		System.out.print(result);
	

	}
	//method that sorts the frequency of the words
	public static <K, V extends Comparable<V> > Map<K, V>
	sortTree(final Map<K, V> treemap)
	{
// We create our comparator 
		Comparator<K> Comparator = new Comparator<K>() {

			public int compare(K k1, K k2)
			{
				int compare = treemap.get(k1).compareTo(
						treemap.get(k2));
				if (compare == 0) {
					return 1;}
				return compare;
			}
		};
		Map<K, V> sorted = new TreeMap<K, V>(Comparator);
		sorted.putAll(treemap);
		return sorted;
	}
}

