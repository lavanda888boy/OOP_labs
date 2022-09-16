#include <stdio.h>
#include <stdlib.h>


struct StackNode{
    int data;
    struct StackNode *next;
};


struct Stack{
    struct StackNode *root;
};


struct Stack* createStack(){
    struct Stack *s = (struct Stack*)malloc(1*sizeof(struct Stack));
    s->root = NULL;

return s;
}


struct StackNode* newNode(int d){
    struct StackNode *stackNode = (struct StackNode*)malloc(1*sizeof(struct StackNode));
    stackNode->data = d;
    stackNode->next = NULL;

return stackNode;
}

int isEmpty(struct StackNode *r){
    return !r;
}


void push(struct Stack *s, int d){
    struct StackNode *stackNode = newNode(d);
    stackNode->next = s->root;
    s->root = stackNode;

    if(!isEmpty(s->root)){
        printf("%d was added into the stack\n", d);
    }
}


int pop(struct Stack *s){
    if(isEmpty(s->root)){
        return INT_MIN;
    }

    struct StackNode *temp = s->root;
    s->root = (s->root)->next;
    int popped = temp->data;

    free(temp);
    return popped;
}


int peak(struct StackNode *r){
    if(isEmpty(r)){
        return INT_MIN;
    }

    return r->data;
}
