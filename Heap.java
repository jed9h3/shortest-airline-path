public class Heap {
    public Node[] arr;
    public int size = 0;

    // ===============================================================================
    public Heap(int sizie) {
        arr = new Node[sizie + 1];
        //buildHeap();
    }

    //public void buildHeap() {
    //    for (int k = 1; k < arr.length; k++) {
    //        arr[k] = new Node();
      //  }
   // }

    public boolean isEmpty() {
        if (arr[1] == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        if (size == arr.length)
            return true;
        else
            return false;
    }

    public void insert(Node c) {
        if (false == isFull()) {
            arr[size + 1] = c;
            size++;
            HeapifyUp(size);
        }
    }

    public Node remove() {
        if (false == isEmpty()) {
            Node root = arr[1];
            arr[1] = arr[size];
            size--;
            HeapifyDown(1);
            return root;
        }
        return null;
    }

    public void HeapifyUp(int index) {
        while (index / 2 != 0) {
            if (arr[index].distance < arr[index / 2].distance) {
                Node temp = arr[index];
                arr[index] = arr[index / 2];
                arr[index / 2] = temp;
                index = index / 2;
            } else {
                break;
            }
        }
    }

    public void HeapifyDown(int index) {
        while (index * 2 <= size) {
            if ((arr[index].distance > arr[index * 2].distance)
                    && (arr[index * 2].distance < arr[index * 2 + 1].distance)) {
                Node temp = arr[index];
                arr[index] = arr[index * 2];
                arr[index * 2] = temp;
                index = index * 2;
            } else if ((arr[index].distance > arr[index * 2 + 1].distance)
                    && (arr[index * 2].distance > arr[index * 2 + 1].distance)) {
                Node temp = arr[index];
                arr[index] = arr[index * 2 + 1];
                arr[index * 2 + 1] = temp;
                index = index * 2 + 1;
            } else {
                break;
            }
        }
    }

    public void showstucture() {
        for (int i = 1; i < arr.length; i++) {
            System.out.print(arr[i].distance + " ");
        }
    }
}