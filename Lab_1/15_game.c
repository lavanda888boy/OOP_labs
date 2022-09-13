#include <stdio.h>


int main(void){

  // initialise the main menu of the game
  while(1){
    printf("Welcome to the main menu of the Game of 15!\n");
    printf("In order to start a new game press 's';\n");
    printf("If you wish to exit the game press 'e';\n");

    char c;
    scanf("%c", &c);

    if(c == 's'){

    } else if(c == 'e'){
      printf("Exiting the game...\n");
      break;
    } else{

    }

  }

  return 0;
}
