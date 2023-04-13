public class LinkList{
    public Link first,last;
    public int count;
    public void addfirst(int data){
        if(count==0){
            first=new Link(data);
            last=first;
        }
        else{
            Link newLink=new Link(data);
            newLink.next=first;
            first=newLink;
        }
        count++;
    }
    public Link getfirst(){
        if(first==null){
            return null;
        }
        return first;
    }
    public boolean removeFirst(){
        if(count==0){
            return false;
        }
        else if(count==1){
            last=first=null;
            count--;
            return true;
        }
        else{
            first=first.next;
            count--;
            
            return true;
        }
    }
    public void addlast(int data){
        if(count==0){
            first=new Link(data);
            last=first;
        }
        else{
            last.next=new Link(data);
            last=last.next;
        }
        count++;
    }
    
    public void add(int data,int index){
        if(index==0){
            addfirst(data);
        }
        else if(index>=count)
            addlast(data);
        else{
            Link temp,current=first;
            for(int i=0;i<index-1;i++){
                current=current.next;

            }
            temp=new Link(data);
            temp.next=current.next;
            current.next=temp;
            count++;
        }
    }
    /*public void addorder(Node data){
        if(count==0){
            addfirst(data);
        }
        else if(count==1){
            if(data<first.data)
                addfirst(data);
            else{
                addlast(data);
            }
        }
        else{
            Link current=first;
            if(current.data>data)
                addfirst(data);
            else{
                Link temp=new Link(data);
                for(int i=0;i<count-1;i++){
                    if(current.next.data>data){
                        temp.next=current.next;
                        current.next=temp;
                        count++;
                        break;
                    }
                    else{
                        if(current.next.next==null)
                            addlast(data);
                        else{
                            current=current.next;
                        }
                    } 
                    
                }
            }
            
        }
    }*/
    /*public LinkList marge(LinkList l2){
        LinkList result=new LinkList();
        if(count==0 && l2.count==0){
            return result;
        }
        else{
            Link p1=first,p2=l2.first;
            if(p1!=null && (p2==null || p1.data<p2.data )){
                result.first=new Link(p1.data);
                result.last=result.first;
                p1=p1.next;
            }
            else{
                result.first=new Link(p2.data);
                result.last=result.first;
                p2=p2.next;
            }
            result.count=count+l2.count;
            for(int i=0;i<result.count-1;i++){
                    if(p1==null){
                        result.last.next=p2;
                        result.last=l2.last;
                        return result;
                    }
                    else if(p2==null){
                        result.last.next=p1;
                        result.last=last;
                        return result;
                    }
                    else if(p1.data<p2.data){
                        result.last.next=new Link(p1.data);
                        p1=p1.next;
                    }
                    else{
                        result.last.next=new Link(p2.data);
                        p2=p2.next;
                    }
                    result.last=result.last.next;
                }
          return result;
        }
    }*/
    public void printList(){
        Link current=first;
        if(count>0){
            while(current.next!=null){
                System.out.print(current.data+" ");
                current=current.next;
            }
            System.out.print(current.data);
        }
    }   
}