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

  // filling the game matrix with the numbers from 0 to 15
  // and then shuffling them randomly
  int gameBoard[4][4];
  int num = 0;
  for(int i = 0; i < 4; i++){
    for(int j = 0; j < 4; j++){
      gameBoard[i][j] = num++;
    }
  }

  // creating the random position for the null/space element
  srand(time(NULL));
  int row = rand() % 4;

  swap(&gameBoard[0][0], &gameBoard[row][0]);

  for(int index = 0; index < 4; index++){
    srand(time(NULL));

    for(int i = 3; i > 0; i--){
      int j = rand() % (i + 1);
      swap(&gameBoard[index][i], &gameBoard[index][j]);
    }
  }

  printTheCurrentStateOfTheGame(gameBoard);

  while(1){
    char option = getchar();
    if(option == 'm'){
      break;
    }

    int direction;
    scanf("%d", &direction);

    int coord[2];

    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        if(gameBoard[i][j] == 0){
          coord[0] = i;
          coord[1] = j;
        }
      }
    }

    processMatrix(gameBoard, direction, coord[0], coord[1]);
    system("clear");

    printMenu();
    printTheCurrentStateOfTheGame(gameBoard);
  }

  return;
}


// function which moves the white space in a certain direction
// genrally the main action in the game
void processMatrix(int matrix[][4], int dir, int nullRow, int nullColumn){
  int temp;

  switch(dir){
    case 4:
      matrix[nullRow][nullColumn] = matrix[nullRow][nullColumn - 1];
      matrix[nullRow][nullColumn - 1] = 0;
      break;

    case 8:
      matrix[nullRow][nullColumn] = matrix[nullRow - 1][nullColumn];
      matrix[nullRow - 1][nullColumn] = 0;
      break;

    case 6:
      matrix[nullRow][nullColumn] = matrix[nullRow][nullColumn + 1];
      matrix[nullRow][nullColumn + 1] = 0;
      break;

    case 2:
      matrix[nullRow][nullColumn] = matrix[nullRow + 1][nullColumn];
      matrix[nullRow + 1][nullColumn] = 0;
      break;

    default:
      printf("\nWrong direction!\n");
  }
}


// utility function to print the instructions of the game
void printMenu(){
  printf("The game starts!\nTo return to the main menu press 'm'.\n");
  printf("Type the direction you wish to move the space and 4, 6, 8, 2.\n");
  printf("The second number will represent the direction of movement.\n");
  printf("Left, right, up, or down respectively.\n\n");
}


// function for printing the matrix of numbers
// representing the current situation in the game
void printTheCurrentStateOfTheGame(int matrix[][4]){
  for(int i = 0; i < 4; i++){
    for(int j = 0; j < 4; j++){
      if(matrix[i][j] == 0){
        printf("   |");
      } else{
        if(matrix[i][j] / 10  ==  0){
          printf(" %d |", matrix[i][j]);
        } else{
          printf("%d |", matrix[i][j]);
        }
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

    char c = getchar();
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
