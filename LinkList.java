public class LinkList {
    public Link first, last;
    public int count;

    public void addfirst(int data) {
        if (count == 0) {
            first = new Link(data);
            last = first;
        } else {
            Link newLink = new Link(data);
            newLink.next = first;
            first = newLink;
        }
        count++;
    }

    public Link getfirst() {
        if (first == null) {
            return null;
        }
        return first;
    }

    public boolean removeFirst() {
        if (count == 0) {
            return false;
        } else if (count == 1) {
            last = first = null;
            count--;
            return true;
        } else {
            first = first.next;
            count--;

            return true;
        }
    }

    public void addlast(int data) {
        if (count == 0) {
            first = new Link(data);
            last = first;
        } else {
            last.next = new Link(data);
            last = last.next;
        }
        count++;
    }

    public void add(int data, int index) {
        if (index == 0) {
            addfirst(data);
        } else if (index >= count)
            addlast(data);
        else {
            Link temp, current = first;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            temp = new Link(data);
            temp.next = current.next;
            current.next = temp;
            count++;
        }
    }

    public void printList() {
        Link current = first;
        if (count > 0) {
            while (current.next != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.print(current.data);
        }
    }
}