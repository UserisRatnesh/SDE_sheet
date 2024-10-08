package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;




public class LearningGraph {


	// TC = O(v*E), since it is a dfs 
	public int[] topoSort(int V, List<Integer>[] adj) {

		Stack<Integer> stk = new Stack<>();

		boolean[] visited = new boolean[V];

		for(int i=0; i<V; i++){
			if(!visited[i]){
				dfs(i, adj, stk, visited);
			}
		}

		int[] ans = new int[V];
		int index = 0;

		while(!stk.isEmpty()){
			ans[index++] = stk.pop();
		}

		return ans;


	}

	public void dfs(int v, List<Integer>[] adj, Stack<Integer> stk, boolean[] visited){

		visited[v] = true;

		for(Integer node : adj[v]){
			if(!visited[node]){
				dfs(node, adj, stk, visited);
			}
		}

		stk.add(v);
	}

	// Topo sort using bfs
	// TC = O(v+E)
	// Kahn's algorithm
	public int[] topoSortBFS(int V, List<Integer>[] adj) {

		int[] indegree = new int[V];

		for(int i=0; i<V; i++){
			for(Integer child : adj[i]){
				indegree[child]++;
			}

		}

		Queue<Integer> que = new LinkedList<>();
		for(int i=0; i<V; i++){
			if(indegree[i] == 0){
				que.add(i);
			}
		}

		int[] ans = new int[V];
		int index = 0;
		while(!que.isEmpty()){
			int node = que.poll();
			ans[index++] = node;
			for(Integer child : adj[node]){
				indegree[child]--;
				if(indegree[child] == 0){
					que.add(child);
				}
			}
		}

		return ans;

	}


	// Finding cycle in directed graph
	// simply i am finding path to the node that is already visited
	public boolean isCyclic(int N, List<Integer>[] adj) {

		boolean[] visited = new boolean[N];
		for(int i=0; i<N; i++){
			if(!visited[i]){
				if(dfs(i, adj, visited)){
					return true;
				}
			}
		}

		return false;

	}

	public boolean dfs(int v, List<Integer>[] adj, boolean[] visited){

		if(visited[v]){
			return true;
		}

		visited[v] = true;
		for(Integer child : adj[v]){
			if(dfs(child, adj, visited)){
				visited[v] = false; // backtracking
				return true;
			}
		}

		visited[v] = false; // backtracking
		return false;
	}

	// Finding cycle in directed graph
	// not intuitive
	public boolean isCyclicImproved(int N, List<Integer>[] adj) {

		boolean[] visited = new boolean[N];
		boolean[] pathVis = new boolean[N];
		for(int i=0; i<N; i++){
			if(!visited[i]){
				if(dfs(i, adj, visited, pathVis)){
					return true;
				}
			}
		}

		return false;

	}

	public boolean dfs(int v, List<Integer>[] adj, boolean[] visited, boolean[] pathVis){

		visited[v] = true;
		pathVis[v] = true;

		for(Integer child : adj[v]){
			if(!visited[child]){
				if(dfs(child, adj, visited, pathVis)) return true;
			}
			else if(pathVis[child]){
				return true;
			}

		}

		pathVis[v] = false;
		return false;

	}

	// Finding cycle in DAG using Kahn's algorithm
	// TC = O
	public boolean isCyclicKahnsAlgo(int N, List<Integer>[] adj) {

		int[] indeg = new int[N];

		for(int i=0; i<N; i++){
			for(Integer child : adj[i]){
				indeg[child]++;
			}
		}

		Queue<Integer> que = new LinkedList<>();
		for(int i=0; i<N; i++){
			if(indeg[i] == 0){
				que.add(i);
			}
		}

		List<Integer> list = new ArrayList<>();
		while(!que.isEmpty()){
			int node = que.poll();
			list.add(node);
			for(Integer child : adj[node]){
				indeg[child]--;
				if(indeg[child] == 0){
					que.add(child);
				}
			}
		}

		return list.size() != N;
	}
	
	
	// Finding safe nodes
	// TC = O(n^2)
	// because dfs is taking O(n^2) because of backtracking
	public int[] eventualSafeNodes(int V, int[][] adj) {

        List<Integer> list = new ArrayList<>();

        boolean[] visited = new boolean[V];

        for(int i=0; i<V; i++){
            if(!visited[i]){
                if(!dfs(i, adj, visited)){
                    list.add(i);
                }
            }
        }

        int[] output = new int[list.size()];
        int index = 0;
        for(Integer v : list){
            output[index++] = v;
        }

        return output;
       
    }


    public boolean dfs(int v, int[][] adj, boolean[] visited){

        visited[v] = true;
        for(Integer child : adj[v]){
            if(!visited[child]){
                if(dfs(child, adj, visited)){
                    return true;
                }
            }else if(visited[child]){
                return true;
            }
        }

        visited[v] = false;
        return false;
    }
    
    
    // TC = O(V+E)
    // Simply as of BFS
    public int[] eventualSafeNodesBetter(int V, int[][] adj) {
        

        int[] indeg = new int[V];
        for (int i = 0; i < V; i++) {
            indeg[i] = adj[i].length;
        }

        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<V; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i=0; i<V; i++){
            for(Integer child : adj[i]){
                adjList.get(child).add(i);
            }
        }   

        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indeg[i] == 0) {
                que.add(i);
            }
        }

        List<Integer> list = new ArrayList<>();

        while (!que.isEmpty()) {
            int node = que.poll();
            list.add(node);
            for (Integer child : adjList.get(node)) {
                indeg[child]--;
                if (indeg[child] == 0) {
                    que.add(child);
                }
            }
        }

        int[] ans = new int[list.size()];
        int index = 0;
        for (Integer node : list) {
            ans[index++] = node;
        }

        Arrays.sort(ans);
        return ans;
    
    }
    
    // Kahn's algorithm
    public boolean courseShedule1(int N, int[][] arr) {

        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0; i<N; i++){
            adj.add(new ArrayList<>());
        }

        int[] indeg = new int[N];

        for(int i=0; i<arr.length; i++){
            int first = arr[i][0];
            int second = arr[i][1];
            adj.get(second).add(first);
            indeg[first]++;
        }

        Queue<Integer> que = new LinkedList<>();
        for(int i=0; i<N; i++){
            if(indeg[i] == 0){
                que.add(i);
            }
        }

        List<Integer> list = new ArrayList<>();
        while(!que.isEmpty()){
            int node = que.poll();
            list.add(node);
            for(Integer child : adj.get(node)){
                indeg[child]--;
                if(indeg[child] == 0){
                    que.add(child);
                }
            }
        }

        return list.size() == N;
    }
    
    
    // Course schedule 2 done
    public int[] courseShedule2(int N, int[][] arr) {
        List<List<Integer>> adj = new ArrayList<>();

        for(int i=0; i<N; i++){
            adj.add(new ArrayList<>());
        }

        int[] indeg = new int[N];

        for(int i=0; i<arr.length; i++){
            int first = arr[i][0];
            int second = arr[i][1];
            adj.get(second).add(first);
            indeg[first]++;
        }

        Queue<Integer> que = new LinkedList<>();
        for(int i=0; i<N; i++){
            if(indeg[i] == 0){
                que.add(i);
            }
        }

        List<Integer> list = new ArrayList<>();
        while(!que.isEmpty()){
            int node = que.poll();
            list.add(node);
            for(Integer child : adj.get(node)){
                indeg[child]--;
                if(indeg[child] == 0){
                    que.add(child);
                }
            }
        }

        if(list.size() != N){
            return new int[0];
        }

        int[] ans = new int[list.size()];
        int index = 0;
        for(int i=0; i<list.size(); i++){
            ans[index++] = list.get(i);
        }

        return ans;
    }
    
    
    // Done using kahn's algorithm
    // TC = O(n*l + kahn's algorithm)
    
    // Two cases when the alien dict is wrong
    // 1. when cycle exists while in the adj list created
    // 2. When abcd appears before abc
    // 	i.e. when two words have common characters and the one with one extra char at last appears before the other
    public String alienDict(String [] dict, int N, int K) {

        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<K; i++){
            adj.add(new ArrayList<>());
        }
        
        int left = 0;
        int right = 1;
        while(right < N){
            String sl = dict[left];
            String rl = dict[right];
            int n = Math.min(sl.length(), rl.length());
            int i=0;
            while(i < n && sl.charAt(i) == rl.charAt(i)){
                i++;
            }
            if(i < sl.length() && i< rl.length()){
                int source = sl.charAt(i)-'a';
                int dest = rl.charAt(i)-'a';
                adj.get(source).add(dest);
            }
            left++;
            right++;
        }

        // System.out.println(adj);
        // now adj list is prepared
        String ans = kahnAlgo(adj, K);

        return ans;

    }

    public String kahnAlgo(List<List<Integer>> adj, int n){
        int[] indeg = new int[n];

        for(int i=0; i<n; i++){
            for(Integer child : adj.get(i)){
                indeg[child]++;
            }
        }

        Queue<Integer> que = new LinkedList<>();
        for(int i=0; i<n; i++){
            if(indeg[i] == 0){
                que.add(i);
            }
        }

        String ans = "";
        while(!que.isEmpty()){
            int node = que.poll();
            ans += (char)(node+'a');
            for(Integer child : adj.get(node)){
                indeg[child]--;
                if(indeg[child] == 0){
                    que.add(child);
                }
            }
        }

        return ans;
    }
    
    
    static class Pair {
        String word;
        int steps;

        Pair(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }
    
    public static int wordLadderLength(String startWord, String targetWord, List<String> wordList) {

        HashSet<String> wordSet = new HashSet<>();
        for(String s : wordList){
            wordSet.add(s);
        }

        if(!wordSet.contains(targetWord)){
            return 0;
        }

        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(startWord, 1));

        while(!que.isEmpty()){
            Pair pair = que.poll();
            String word = pair.word;
            int steps = pair.steps;

            // go to each index of word and change it form a to z and check if it exists 
            // in wordSet or not 
            // if yes remove from word set and add to graph
            // and put it in que
            // else
            // do nothing
            for(int i=0; i<word.length(); i++){
                for(char j='a'; j<='z'; j++){
                    String newWord = word.substring(0,i) + j + word.substring(i+1);
                    if(newWord.equals(targetWord)){
                        return steps + 1;
                    }
                    if(wordSet.contains(newWord)){
                        que.add(new Pair(newWord, steps+1));
                        wordSet.remove(newWord);
                    }
                }
            }
        }

        return 0;
     
    }
    
    
    public List<List<String>> findSequencesWordLadderII(String startWord, String targetWord, List<String> wordList) {


        HashSet<String> wordSet = new HashSet<>();
        for(String s : wordList){
            wordSet.add(s);
        }

        List<List<String>> ans = new ArrayList<>();

        Queue<List<String>> que = new LinkedList<>();
        que.add(new ArrayList<>(Arrays.asList(startWord)));

        HashSet<String> visitSet = new HashSet<>();
        visitSet.add(startWord);

        while(!que.isEmpty()){
            
            int levelCount = que.size();

            while(levelCount --> 0){
                List<String> list = que.poll();
                String str = list.get(list.size()-1);

                // if str is the targetWord
                // do not need further searching
                if(str.equals(targetWord)){
                    ans.add(list);
                    continue;
                }

                // Do convert each char of this string go get 
                // string that is present in wordset
                for(int i=0; i<str.length(); i++){
                    for(char c='a'; c<='z'; c++){
                        String newWord = str.substring(0, i) + c + str.substring(i+1);
                        if(!wordSet.contains(newWord)){
                            continue;
                        }

                        List<String> newList = new ArrayList<>(list);
                        newList.add(newWord);
                        que.add(newList);
                        visitSet.add(newWord);
                    }
                }
            }

            // iterate over visiteSet and remove all elements that is common
            // in visitSet and wordSet
            for(String s : visitSet){
                if(wordSet.contains(s)){
                    wordSet.remove(s);
                }
            }

            visitSet.clear();
        }

        return ans;

    }



	public static void main(String[] args) 
	{
		
	}

}
