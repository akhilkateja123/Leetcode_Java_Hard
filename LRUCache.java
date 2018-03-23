package aws.amazon.practice;

import java.util.HashMap;
import java.util.Map;

class Node{
	Node pre;
	Node next;
	int key;
	int value;
	
	Node(int key, int value){
	   this.key=key;
	   this.value=value;
	}
	
}

public class LRUCache {

	Map<Integer, Node> map;
	int count=0;
	Node head;
	Node tail;
	int capacity;

	public LRUCache(int capacity) {
		map= new HashMap<>();
		head= new Node(0,0);
		tail= new Node(0,0);
		head.next=tail;
		tail.pre= head;
		head.pre=null;
		tail.next=null;
	}
	
	public void delete(Node node) {
		node.pre.next= node.next;
		node.next.pre=node.pre;
	}
	
	public void addToHead(Node node) {
		node.next=head.next;
		node.pre=head;
		head.next.pre=node;
		head.next=node;
	}

	public int get(int key) {
		if(map.get(key)!=null) {
			Node node= map.get(key);
			int result= node.value;
			delete(node);
			addToHead(node);
			return result;
		}
		
		return -1;
	}

	public void put(int key, int value) {
		if(map.get(key)!=null) {
			Node node= map.get(key);
			node.value=value;
			delete(node);
			addToHead(node);
			
		}else {
			Node node= new Node(key, value);
			map.put(key, node);
			if(count<capacity) { count++; addToHead(node);
			}else {
				map.remove(tail.pre.key);
				delete(tail.pre);
				addToHead(node);
			}
			
		}
	}

}

