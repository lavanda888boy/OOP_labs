#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// simple function for swapping two numbers
void swap(int *a, int *b){
  int temp = *a;
  *a = *b;
  *b = temp;
}


// main function representing the gaming process itself
void startTheGame(){
  printf("The game starts!\nTo return to the main menu press '0 0'.\n");
  printf("Type the number you wish to move and 4, 6, 8, 2 with a single space.\n");
  printf("The second number will represent the direction of movement.\n");
  printf("Left, right, up, or down respectively.\n\n");

  // filling the game matrix with the numbers from 0 to 15
  // and then shuffling them randomly
  int gameBoard[4][4];
  int num = 0;
  for(int i = 0; i < 4; i++){
    for(int j = 0; j < 4; j++){
      gameBoard[i][j] = num++;
    }
  }

  for(int index = 0; index < 4; index++){
    srand(time(NULL));

    for(int i = 3; i > 0; i--){
      int j = rand() % (i + 1);
      swap(&gameBoard[index][i], &gameBoard[index][j]);
    }
  }

  printTheCurrentStateOfTheGame(gameBoard);

  while(1){
    int number, direction;
    scanf("%d %d", &number, &direction);

    if(number == 0  &&  direction == 0){
      break;
    }

    printTheCurrentStateOfTheGame(gameBoard);
  }

  return;
}

// function for printing the matrix of numbers
// representing the current situation in the game
void printTheCurrentStateOfTheGame(int matrix[][4]){
  for(int i = 0; i < 4; i++){
    for(int j = 0; j < 4; j++){
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
    system("clear");
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
