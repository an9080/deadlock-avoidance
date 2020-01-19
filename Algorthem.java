//anoud - sahad-leen  Group: 15
package algorthem;

import java.util.*;

public class Algorthem { 
	static int countB=0;

	static int countT=0;
	
	public static int heapSize;
	 public int capacity;
	public int [] mH;
	public int currentSize;
    public static void main(String[] args) {      
        System.out.println("----------------------------------------------------------------------");
        System.out.println("-----------------------Binary heap sort ------------------------------");
        System.out.println("----------------------------------------------------------------------");

        for(int i =10000 ; i <=100000; i+=10000) {
        	 int arrbinary[]=new int[i];
        	 int n =i;
        	 generatebinary(arrbinary,n);
        }
        System.out.println("----------------------------------------------------------------------");
        System.out.println("-----------------------Thulathi Heap Sort-----------------------------");
        System.out.println("----------------------------------------------------------------------");

        for(int i =10000 ; i <=100000; i+=10000) {
       	 int arrThulathi[] = new int [i] ;
       	 int n =i;
       	 generateTulathi(arrThulathi,n);
       }
      
    }
    //--------------------------------- binary ---------------------------------------------------------------
    public static void generatebinary(int arr[], int n){
    	  
    	//now assigning random values to it
    	Random r = new Random();
    	for(int i=0;i<n;i++)
    	{
    	int k = r.nextInt()%10000;
    	if(k<0)k=k*-1;
    	arr[i]=k;
    	}

    	long s = System.nanoTime();//to find out time
    	Algorthem.heapsort(arr);
    	long e =System.nanoTime();
    	System.out.println("Array size :"+n+", comparisions :"+countB+", timetaken :"+(e-s)+" nanosecs");

    	   
    	   }
    //--------------------------------generateTulathi------------------------------------------
    public static void generateTulathi(int arr[], int n){
    	//now assigning random values to it
    	Random r = new Random();
    	for(int i=0;i<n;i++)
    	{
    	int k = r.nextInt()%10000;
    	if(k<0)k=k*-1;
    	arr[i]=k;
    	}
    	long s = System.nanoTime();//to find out time
    	Algorthem.ThulathiHeapSort(arr); 
    	long e =System.nanoTime();
    	System.out.println("Array size :"+n+", comparisions :"+countT+", timetaken :"+(e-s)+" nanosecs");

    	   }
   
    	
    
  //---------------------------------heapsort-------------------------------- 
    public static void heapsort(int arr[]) 
    { 
    	
    	
        int n = arr.length; 
          for (int i = n / 2 - 1; i >= 0; i--) 
        	  sort(arr, n, i); 
          for (int i=n-1; i>=0; i--) 
        { 
            int temp = arr[0]; 
            arr[0] = arr[i]; 
            arr[i] = temp; 
            sort(arr, i, 0);
            
        } }
        
        static void sort(int arr[], int n, int i) 
        { 
            int largest = i; 
            int l = 2*i + 1; 
            int r = 2*i + 2;  
            if (l < n && arr[l] > arr[largest]) 
             largest = l; 
            countB++;
            
            if (r < n && arr[r] > arr[largest]) 
                largest = r; 
            countB++;
            if (largest != i) 
            { 
            	swap(i, largest,arr);
                sort(arr, n, largest); 
            }    countB++;
 }
        
    
        
//-----------------------------------Thulathi Heap Sort ------------------------------ 
      
        public static void ThulathiHeapSort(int[] arr){
    	    int n = arr.length; 
    	        for (int i = (n/3) -1 ; i >= 0; i--) 
    	            heapify(arr, n, i); 
    	        for (int i=n-1; i>=0; i--) 
    	        { 
    	            int temp = arr[0]; 
    	            arr[0] = arr[i]; 
    	            arr[i] = temp; 
    	            heapify(arr, i, 0); 
    	        } 
    	      
    	      
    	}
        
        static void heapify(int arr[], int n, int i) 
	    { 
	    int largest=i;
	    
	      int l = (3*i)+1; // first child
	      
	      int mid= (3*i)+2;//middle one
	      
	      int r = (3*i)+3; // right child


	    
	        if (l!=-1 && l < n && arr[l] > arr[largest]) 
	            largest = l; 
	            
	            countT++;
	    
	    if (mid < n && arr[mid] > arr[largest]) 
	    
	    
	            largest = mid; 
	            
	     countT++;
	              if (r < n && arr[r] > arr[largest]) 
	            largest = r; 
	     countT++;

	        if (largest != i) 
	        { 
	            int swap = arr[i]; 
	            
	            arr[i] = arr[largest]; 
	            
	            arr[largest] = swap; 
	  
	            heapify(arr,n,largest); 
	        } 
	        
	      countT++;
	        
	    } 
	    
        //===============================In General===========================================//

        static void swap(int i, int j,int array [] ){
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        
    
}
