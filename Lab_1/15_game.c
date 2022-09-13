#include <stdio.h>

// main function representing the gaming process itself
void startTheGame(){
  printf("The game starts!\nTo return to the main menu press 'm'\n.");
  printf("Type the number you wish to move and 4, 6, 8, 2 with a single space.\n");
  printf("The second number will represent the direction of movement.\n");
  printf("Left, right, up, or down respectively.\n\n");

  int gameBoard[4][4];
  printTheCurrentStateOfTheGame(gameBoard);

  while(1){
    int number, direction;
    scanf("%d %d", &number, &direction);

    printTheCurrentStateOfTheGame(gameBoard);
  }

  return;
}

// function for printing the matrix of numbers
// representing the current situation in the game
void printTheCurrentStateOfTheGame(int matrix[][4]){
  for(int i = 1; i <= 4; i++){
    for(int j = 1; j <= 4; j++){
      if(matrix[i][j] == 0){
        printf(" ");
      } else{
        printf("%d ", matrix[i][j]);
      }
    }
    printf("\n");
  }
  printf("\n");
}


int main(void){

  // initialise the main menu of the game
  while(1){
    printf("Welcome to the main menu of the Game of 15!\n");
    printf("In order to start a new game press 's'.\n");
    printf("If you wish to exit the game press 'e'.\n");

    char c;
    scanf("%c", &c);
    printf("\n");

    // choosing the options of the game
    if(c == 's'){
      startTheGame();
    } else if(c == 'e'){
      printf("Exiting the game...\n");
      break;

    } else{

    }

  }

  return 0;
}
