#include "Image.h"
#include <iostream>

int main(int argc, char* argv[])
{
  char* filePath = argv[1];
  Image newImage(filePath);
  newImage.fetchImage();

  return 0;
}