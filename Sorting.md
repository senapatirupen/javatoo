##### All the sorting code is available on at "com.example.javatoo.challenge.array.arraysort" location
#### Bubble Sort
![Alt text](images/bubbleSort.PNG?raw=true "Bubble Sort")
    
 ```
public static void bubbleSort(int[] arr) {
  int n = arr.length;
  for (int i = 0; i < n - 1; i++) {
    for (int j = 0; j < n - i - 1; j++) {
      if (arr[j] > arr[j + 1]) {
        int temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
      }
    }
  }
}
```
#### Insertion Sort
![Alt text](images/insertionSort.PNG?raw=true "Insertion Sort")
    
 ```
public static void insertionSort(int arr[]) {
  int n = arr.length;
  for (int i = 1; i < n; ++i) {
    int key = arr[i];
    int j = i - 1;
    while (j >= 0 && arr[j] > key) {
      arr[j + 1] = arr[j];
      j = j - 1;
    }
    arr[j + 1] = key;
  }
}
```
#### Counting Sort
![Alt text](images/countingSort.PNG?raw=true "Counting Sort")
```
The minimum element is 2 and the maximum element is 8. The new array, counts, will have a size equal to the maximum minus the minimum + 1 = 8 - 2 + 1 = 7.
Counting each element will result in the following array (counts[arr[i] - min]++):counts[2] = 1 (4); counts[0] = 2 (2); counts[4] = 2 (6);
counts[6] = 1 (8); counts[3] = 1 (5);
Now, we must loop this array and use it to reconstruct the sorted array as in the following implementation:

public static void countingSort(int[] arr) {
  int min = arr[0];
  int max = arr[0];
  for (int i = 1; i < arr.length; i++) {
    if (arr[i] < min) {
      min = arr[i];
    } else if (arr[i] > max) {
      max = arr[i];
    }
  }
  int[] counts = new int[max - min + 1];
  for (int i = 0; i < arr.length; i++) {
    counts[arr[i] - min]++;
  }
  int sortedIndex = 0;
  for (int i = 0; i < counts.length; i++) {
    while (counts[i] > 0) {
      arr[sortedIndex++] = i + min;
      counts[i]--;
    }
  }
}
This is a very fast algorithm.
```

#### Heap Sort
![Alt text](images/heapSort.PNG?raw=true "Heap Sort")
![Alt text](images/heapSort2.PNG?raw=true "Heap Sort")
```
public static void heapSort(int[] arr) {
  int n = arr.length;
  buildHeap(arr, n);
  while (n > 1) {
    swap(arr, 0, n - 1);
    n--;
    heapify(arr, n, 0);
  }
}

private static void buildHeap(int[] arr, int n) {
  for (int i = arr.length / 2; i >= 0; i--) {
    heapify(arr, n, i);
  }
}

private static void heapify(int[] arr, int n, int i) {
  int left = i * 2 + 1;
  int right = i * 2 + 2;
  int greater;

  if (left < n && arr[left] > arr[i]) {
    greater = left;
  } else {
    greater = i;
  }

  if (right < n && arr[right] > arr[greater]) {
    greater = right;
  }

  if (greater != i) {
    swap(arr, i, greater);
    heapify(arr, n, greater);
  }
}

private static void swap(int[] arr, int x, int y) {
  int temp = arr[x];
  arr[x] = arr[y];
  arr[y] = temp;
}
```