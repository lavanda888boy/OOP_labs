#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int gameBoard[4][4];
int *nullRow;
int *nullColumn;
int d;


// simple function for swapping two numbers
void swap(int *a, int *b){
  int temp = *a;
  *a = *b;
  *b = temp;
}


// main function representing the gaming process itself
void startTheGame(){

  system("clear");
  printMenu();

  // filling the game matrix with the numbers from 0 to 15
  // and then shuffling them randomly
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

  printTheCurrentStateOfTheGame();

  for(int i = 0; i < 4; i++){
    for(int j = 0; j < 4; j++){
      if(gameBoard[i][j] == 0){
        *nullRow = i;
        *nullColumn = j;
        break;
      }
    }
  }

  int direction;

  while(1){
    d = direction;
    scanf("%d", &direction);

    if(direction == -1){
      undoChanges(d);
    } else{
      processMatrix(direction);
    }

    system("clear");
    printMenu();
    printTheCurrentStateOfTheGame();

    char option = getchar();

    if(option == 'm'){
      break;
    }
  }

  return;
}


// function which moves the white space in a certain direction
// genrally the main action in the game
void processMatrix(int dir){
  switch(dir){
    case 4:
      gameBoard[*nullRow][*nullColumn] = gameBoard[*nullRow][(*nullColumn) - 1];
      gameBoard[*nullRow][(*nullColumn) - 1] = 0;
      (*nullColumn)--;
      break;

    case 8:
      gameBoard[*nullRow][*nullColumn] = gameBoard[(*nullRow) - 1][*nullColumn];
      gameBoard[(*nullRow) - 1][*nullColumn] = 0;
      (*nullRow)--;
      break;

    case 6:
      gameBoard[*nullRow][*nullColumn] = gameBoard[*nullRow][(*nullColumn) + 1];
      gameBoard[*nullRow][(*nullColumn) + 1] = 0;
      (*nullColumn)++;
      break;

    case 2:
      gameBoard[*nullRow][*nullColumn] = gameBoard[(*nullRow) + 1][*nullColumn];
      gameBoard[(*nullRow) + 1][*nullColumn] = 0;
      (*nullRow)++;
      break;

    default:

  }
}


// function which reverts the last move made by the player
void undoChanges(int dir){
  if(dir == 4){
    processMatrix(6);
    return;
  } else if(dir == 6){
    processMatrix(4);
    return;
  } else if(dir == 8){
    processMatrix(2);
    return;
  } else if(dir == 2){
    processMatrix(8);
    return;
  }
}


// utility function to print the instructions of the game
void printMenu(){
  printf("\033[34m\033[101mThe game starts!\nTo return to the main menu press 'm'.\033[0m\n");
  printf("\033[34m\033[101mType the direction you wish to move the space and 4, 6, 8, 2.\033[0m\n");
  printf("\033[34m\033[101mLeft, right, up, or down respectively.\033[0m\n");
  printf("\033[34m\033[101mType -1 to undo your last move.\033[0m\n");
}


// function for printing the matrix of numbers
// representing the current situation in the game
void printTheCurrentStateOfTheGame(){
  for(int i = 0; i < 4; i++){
    for(int j = 0; j < 4; j++){
      if(gameBoard[i][j] == 0){
        printf("\033[30m\033[107m   |\033[0m");
      } else{
        if(gameBoard[i][j] / 10  ==  0){
          printf("\033[30m\033[107m %d |\033[0m", gameBoard[i][j]);
        } else{
          printf("\033[30m\033[107m%d |\033[0m", gameBoard[i][j]);
        }
      }
    }
    printf("\n");
  }
  printf("\n");
}


int main(void){

  nullRow = (int*)malloc(1*sizeof(int));
  nullColumn = (int*)malloc(1*sizeof(int));

  // initialise the main menu of the game
  while(1){
    system("clear");
    printf("\033[31m\033[102mWelcome to the main menu of the Game of 15!\033[0m\n");
    printf("\033[31m\033[102mIn order to start a new game press 's'.\033[0m\n");
    printf("\033[31m\033[102mIf you wish to exit the game press 'e'.\033[0m\n");

    char c = getchar();
    printf("\n");

    // choosing the options of the game
    if(c == 's'){
      startTheGame();
    } else if(c == 'e'){
      printf("\033[91mExiting the game...\033[0m\n");
      break;

    } else{

    }

  }

  return 0;
}
