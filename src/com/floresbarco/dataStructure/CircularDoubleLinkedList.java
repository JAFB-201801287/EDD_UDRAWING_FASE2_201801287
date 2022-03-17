package com.floresbarco.dataStructure;

public class CircularDoubleLinkedList<T> {
    private Integer index;
    private Integer count;
    private Node<T> head;

    // CONSTRUCTOR
    public CircularDoubleLinkedList() {
        this.index = 1;
        this.count = 0;
        this.head = null;
    }

    // GET
    public Node<T> get() {
        return this.head;
    }

    public Integer getCount() { return count; }

    // ADD
    public void add(T element){
        Node<T> newNode = new Node<T>(this.index ,element);
        if(this.head==null) {
            this.head = newNode;
            this.head.setAfter(this.head);
            this.head.setBefore(this.head);
        } else{
            Node<T> current = this.head;
            while(current.getAfter() != this.head)
                current = current.getAfter();
            current.setAfter(newNode);
            newNode.setBefore(current);
            newNode.setAfter(this.head);
            this.head.setBefore(newNode);
        }
        this.index++;
        this.count++;
    }


    // DEQUEUE
    public T delete(Integer id) {
        if(this.head != null){
            Node<T> aux= this.head;
            Node<T> before = null;
            T element = null;
            while(aux.getAfter() != this.head){
                if(aux.getId().equals(id)){
                    element = aux.getElement();
                    if(before == null){
                        if(aux.getAfter()== this.head)
                            this.head =null;
                        else{
                            before = aux.getBefore();
                            before.setAfter(aux.getAfter());
                            aux = aux.getAfter();
                            aux.setBefore(before);
                            this.head = aux;
                            before = null;
                        }
                    }else{
                        aux.setBefore(null);
                        before.setAfter(aux.getAfter());
                        aux=aux.getAfter();
                        aux.setBefore(before);
                    }
                }else{
                    before = aux;
                    aux = aux.getAfter();
                }
            }
            this.count--;
            return element;
        }
        return null;
    }


}
