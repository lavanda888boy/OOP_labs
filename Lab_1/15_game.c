#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int gameBoard[4][4];
int *nullRow;
int *nullColumn;
int d;
int restartMarker;
int victoryMarker;


// simple function for swapping two numbers
void swap(int *a, int *b){
  int temp = *a;
  *a = *b;
  *b = temp;
}


// main function representing the gaming process itself
int startTheGame(){

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
    victoryMarker = checkVictory();
    if(victoryMarker == 1){
      return 0;
    }

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
    } else if(option == 'r'){
      return 1;
    }
  }

  return 0;
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


// function which returns 1 if the game is won
// there can be two winning situations when the space
// is situated in the upper left corener of the matrix
// or in the botoom right
int checkVictory(){
  int lineMatrix[16];
  int index = 0;

  for(int i = 0; i < 4; i++){
    for(int j = 0; j < 4; j++){
      lineMatrix[index++] = gameBoard[i][j];
    }
  }

  if(lineMatrix[0] == 0){
    for(int k = 1; k < 16; k++){
      if(lineMatrix[k] - 1  !=  lineMatrix[k - 1]){
          return 0;
      }
    }
  } else if(lineMatrix[15] == 0){
    for(int k = 1; k < 15; k++){
      if(lineMatrix[k] - 1  !=  lineMatrix[k - 1]){
          return 0;
      }
    }
  } else{
    return 0;
  }

  return 1;
}


// utility function to print the instructions of the game
void printMenu(){
  if(restartMarker == 0){
    printf("\033[34m\033[101mThe game starts!\nTo return to the main menu press 'm'.\033[0m\n");
  } else{
    printf("\033[34m\033[101mThe new game started!!!\nTo return to the main menu press 'm'.\033[0m\n");
  }

  printf("\033[34m\033[101mTo restart the current game press 'r'.\033[0m\n");
  printf("\033[34m\033[101mType the direction you wish to move the space and 4, 6, 8, 2.\033[0m\n");
  printf("\033[34m\033[101mLeft, right, up, or down respectively.\033[0m\n");
  printf("\033[34m\033[101mType -1 to undo your last move.\033[0m\n\n");
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

    if(restartMarker == 0){
      system("clear");
      printf("\033[31m\033[102mWelcome to the main menu of the Game of 15!\033[0m\n");
      printf("\033[31m\033[102mIn order to start a new game press 's'.\033[0m\n");
      printf("\033[31m\033[102mIf you wish to exit the game press 'e'.\033[0m\n");

      char c = getchar();
      printf("\n");

      // choosing the options of the game
      if(c == 's'){
        restartMarker = startTheGame();
      } else if(c == 'e'){
        printf("\033[91mExiting the game...\033[0m\n");
        free(nullRow);
        free(nullColumn);
        break;

      } else if(victoryMarker == 1){
        system("clear");
        printf("\033[31m\033[102mCongratulations!!! You won the game!!!\033[0m\n");
        printf("\033[31m\033[102mIn order to start a new game press 's'.\033[0m\n");
        printf("\033[31m\033[102mIf you wish to exit the game press 'e'.\033[0m\n");
      } else{
        
      }
    } else{
      restartMarker = startTheGame();
    }



  }

  return 0;
}
