#include "Image.h"
#include <iostream>
#include <fstream>

Image::Image(const std::string& filePath)
  : filePath(filePath)
{
}

std::string Image::getFilePath() const
{
  return this->filePath;
}

void Image::fetchImage()
{
  std::ifstream fetchedFile(filePath);
  if (!fetchedFile.is_open()) {
    std::cout << "Error opening " << filePath << std::endl;
  }

  unsigned int width = 0, height = 0;
  fetchedFile.read(reinterpret_cast<char*>(&width), 4);
  fetchedFile.read(reinterpret_cast<char*>(&height), 4);
  std::cout << "Width: " << (width)/1024 << std::endl;
  std::cout << "Height: " << (height)/1024 << std::endl;
}
