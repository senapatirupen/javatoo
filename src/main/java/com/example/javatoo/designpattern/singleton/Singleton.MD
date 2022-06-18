Method 4 (Best): Use “Double Checked Locking” 
If you notice carefully once an object is created synchronization is no longer useful because now obj will not be null and any sequence of operations will lead to consistent results. 
So we will only acquire lock on the getInstance() once, when the obj is null. This way we only synchronize the first way through, just what we want. 

<code>
// Double Checked Locking based Java implementation of
// singleton design pattern
class Singleton {
	private static volatile Singleton obj = null;
	private Singleton() {}
	public static Singleton getInstance()
	{
		if (obj == null)
		{
			// To make thread safe
			synchronized (Singleton.class)
			{
				// check again as multiple threads
				// can reach above step
				if (obj==null)
					obj = new Singleton();
			}
		}
		return obj;
	}
}
</code>

We have declared the obj volatile which ensures that multiple threads offer the obj variable correctly when it is being initialized to Singleton instance. This method drastically reduces the overhead of calling the synchronized method every time.