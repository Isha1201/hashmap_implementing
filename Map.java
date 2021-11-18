import java.util.*;

public class Map<K,V> {
	ArrayList<MapNode<K,V>> buckets;
	int size;
	int bucketsize;
	Map(){
		bucketsize=5;
		buckets=new ArrayList<MapNode<K,V>>();
		for(int i=0;i<bucketsize;i++) {
			buckets.add(null);
		}
		size=0;
	
		
	}
	private int getindex(K key){
		int hashcode=key.hashCode();
		return hashcode%bucketsize;
		
	}
	public void rehash() {
		ArrayList<MapNode<K,V>> temp=buckets;
		buckets=new ArrayList<>();
		for(int i=0;i<2*bucketsize;i++) {
			buckets.add(null);
		}
		bucketsize *=2;
		size=0;
		for(int i=0;i<temp.size();i++) {
			MapNode<K,V> head=temp.get(i);
			while(head!=null) {
				K key=head.key;
				V value=head.value;
				insert(key,value);
				head=head.next;
				
			}
		}
	}
	public void insert(K key,V value) {
		int index=getindex(key);
		MapNode<K,V> head=buckets.get(index);
		while(head!=null) {
			if(head.key.equals(key)) {
				head.value=value;
				return;
			}
			head=head.next;
			
		}
		
		MapNode<K,V> toinsert=new MapNode<K,V>(key,value);
		head=buckets.get(index);
		toinsert.next=head;
		
//		toinsert=head;
	
		buckets.set(index, toinsert);
		
		size++;
		
		double loadfact=(1.0*size)/bucketsize;
		if(loadfact>0.7) {
			rehash();
		}
		
		
			
			
		
		
	}
	public V remove(K key) {
		int index=getindex(key);
		MapNode<K,V> head=buckets.get(index);
		MapNode<K,V> prev=null;
		while(head!=null) {
			if(head.key.equals(key)) {
				if(prev==null) {
					buckets.set(index,head.next);
					
				}
				else {
				prev.next=head.next;
				}
				size--;
				return head.value;
			}
			prev=head;
			head=head.next;
		}
		return null;
	}
	public V getvalue(K key) {
		int getindex=getindex(key);
		MapNode<K,V> head=buckets.get(getindex);
		while(head!=null) {
			if(head.key.equals(key)) {
				return head.value;
			}
			head=head.next;
		}
		return null;
	}
}
