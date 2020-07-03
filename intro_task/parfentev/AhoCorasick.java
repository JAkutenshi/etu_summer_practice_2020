import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

class SingleLinkedList<T> implements Iterable<T> {
	static class Node<T> {
		private T object;
		private Node<T> next;
		
		public T getObject() {
			return object;
		}
		
		public void setObject(T object) {
			this.object = object;
		}
		
		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		public Node(T object, Node<T> next) {
			this.object = object;
			this.next = next;
		}
	}
	
	private Node<T> first = null;
	private Node<T> last = null;
	
	public void addLast(T x) {
		Node<T> n = new Node<>(x, null);
		if (last != null) {
			last.setNext(n);
		} else {
			first = n;
		}
		last = n;
	}
	
	public T removeFirst() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		Node<T> n = first;
		first = first.getNext();
		if (first == null) {
			last = null;
		}
		return n.getObject();
	}
	
	static class ListIterator<T> implements Iterator<T> {
		private Node<T> current;

		public ListIterator(Node<T> current) {
			this.current = current;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (current == null) {
				throw new NoSuchElementException();
			}
			Node<T> n = current;
			current = current.getNext();
			return n.getObject();
		}
	}
	
	public Iterator<T> iterator() {
		return new ListIterator<>(first);
	}
	
	public boolean isEmpty() {
		return first == null;
	}
}

public class AhoCorasick {

    static class Result {
        private final int index;
        private final int length;
        private Result next = null;

        public Result(int index, int length) {
			this.index = index;
			this.length = length;
		}

		public int getIndex() {
			return index;
		}

		public int getLength() {
			return length;
		}

		public Result getNext() {
			return next;
		}

		public void setNext(Result next) {
			this.next = next;
		}
    }

    static class Transition {
        private final char ch;
        private final State dest;

		public char getCharacter() {
			return ch;
		}

		public State getDestination() {
			return dest;
		}

		public Transition(char ch, State dest) {
			this.ch = ch;
			this.dest = dest;
		}
    }

    static class State {
        private final SingleLinkedList<Transition> transitions
        	= new SingleLinkedList<>();
        private State fallback = null;
        private Result first_result = null;
        private Result last_result = null;

		public State getFallback() {
			return fallback;
		}

		public void setFallback(State fallback) {
			this.fallback = fallback;
		}

        private void chainResultsToBack(Result res) {
            if (last_result != null) {
                last_result.setNext(res);
            } else {
                first_result = res;
            }
        }

        public void addResult(int index, int length) {
            Result res = new Result(index, length);
            chainResultsToBack(res);
            last_result = res;
        }
        
        static class ResultIterator implements Iterator<Result> {
        	private Result current;

			public ResultIterator(Result current) {
				this.current = current;
			}

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Result next() {
				if (current == null) {
					throw new NoSuchElementException();
				}
				Result prev = current;
				current = current.getNext();
				return prev;
			}
        }
        
        public Iterable<Result> results() {
        	return () -> new ResultIterator(first_result);
        }
        
        public void addTransition(char ch, State s) {
        	transitions.addLast(new Transition(ch, s));
        }

        public Transition findTransition(char ch) {
            for (Transition tr : transitions) {
                if (tr.getCharacter() == ch) {
                    return tr;
                }
            }
            return null;
        }
        
        public void extendForest(String pattern, int index) {
        	State s = this;
        	for (int i = 0; i < pattern.length(); i++) {
        		char c = pattern.charAt(i);
        		
        		Transition t = s.findTransition(c);
        		if (t != null) {
        			s = t.getDestination();
        		} else {
        			State ns = new State();
        			s.addTransition(c, ns);
        			s = ns;
        		}
        	}
        	
        	s.addResult(index, pattern.length());
        }
        
        public static State buildForest(Iterable<String> patterns) {
        	State root = new State();
        	int idx = 0;
        	for (String s : patterns) {
        		root.extendForest(s, idx++);
        	}
        	return root;
        }
        
        private static State findChildFallback(State parent, char ch) {
        	for (State s = parent; s != null; s = s.getFallback()) {
        		Transition t = s.findTransition(ch);
        		if (t != null) {
        			return t.getDestination();
        		}
        	}
        	return null;
        }
        
        public void turnIntoStateMachine() {
        	SingleLinkedList<State> q = new SingleLinkedList<>();
        	q.addLast(this);
        	
        	while (!q.isEmpty()) {
        		State s = q.removeFirst();
        		State f = s.getFallback();
        		for (Transition t : s.transitions) {
        			State d = t.getDestination();
        			q.addLast(d);
        			
        			State df = findChildFallback(f, t.getCharacter());
        			if (df == null) {
        				df = this;
        			}
        			d.setFallback(df);
        			d.chainResultsToBack(df.first_result);
        		}
        	}
        }
    }
    
    static class Match {
    	final private int index;
    	final private int start;
    	
		public int getIndex() {
			return index;
		}
		
		public int getStart() {
			return start;
		}

		public Match(int index, int start) {
			this.index = index;
			this.start = start;
		}
    }
    
    static class Matcher implements Iterator<Match> {
    	private State state;
    	private final SingleLinkedList<Match> matches
    		= new SingleLinkedList<>();
    	private final String string;
    	private int index = 0;

		public Matcher(State state, String string) {
			this.state = state;
			this.string = string;
		}
    	
    	private boolean step(char ch) {
    		for (;;) {
    			Transition t = state.findTransition(ch);
    			if (t != null) {
    				state = t.getDestination();
    				return true;
    			}
    			
    			State f = state.getFallback();
    			if (f != null) {
    				state = f;
    			} else {
    				return false;
    			}
    		}
    	}
    	
    	private void stepAndRecord() {
    		int offset = index++;
    		if (step(string.charAt(offset))) {
    			for (Result r : state.results()) {
    				int start = offset - r.getLength() + 1;
    				matches.addLast(new Match(r.getIndex(), start));
    			}
    		}
    	}

		@Override
		public boolean hasNext() {
			while (matches.isEmpty()) {
				if (index >= string.length()) {
					return false;
				}
				stepAndRecord();
			}
			return true;
		}

		@Override
		public Match next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return matches.removeFirst();
		}
    }

    public static void main(String[] args) {
    	String search_s;
    	SingleLinkedList<String> patterns = new SingleLinkedList<>();
        try (Scanner s = new Scanner(System.in)) {
        	search_s = s.nextLine();
        	for (int n = Integer.parseInt(s.nextLine()); n-- != 0;) {
        		patterns.addLast(s.nextLine());
        	}
        }
        
        State s = State.buildForest(patterns);
        s.turnIntoStateMachine();
        
        Matcher mat = new Matcher(s, search_s);
        Stream.generate(() -> null)
        	.takeWhile(x -> mat.hasNext())
        	.map(x -> mat.next())
        	.sorted(Comparator.comparingInt(Match::getStart)
        				.thenComparingInt(Match::getIndex))
        	.forEach(m -> 
        		System.out.printf("%d %d\n", m.getStart()+1, m.getIndex()+1));
    }

}
