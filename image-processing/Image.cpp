#include "Image.h"
#include <iostream>
#include <fstream>
#include <sstream>

Image::Image(const std::string& filePath)
  : filePath(filePath)
{
}

std::string Image::getFilePath() const
{
  return this->filePath;
}

int Image::getWidth() const
{
  return this->width;
}

int Image::getHeight() const
{
  return this->height;
}

void Image::fetchImage()
{
  std::ifstream fetchedFile(filePath, std::ios::in | std::ios::binary);
  if (!fetchedFile.is_open()) {
    std::cout << "Error opening " << filePath << std::endl;
  }
  std::stringstream ss;
  std::string inputLine = "";

  int width = 0, height = 0;

  std::getline(fetchedFile, inputLine);
  std::cout << "Version: " << inputLine << std::endl;

  // std::getline(fetchedFile, inputLine);
  // std::cout << "Comment: " << inputLine << std::endl;

  ss << fetchedFile.rdbuf();
  ss >> this->width;
  ss >> this->height;
  std::cout << this->width << " columns and " << this->height << " rows" << std::endl;
  int Q = 0;
  ss >> Q;
  ss.ignore();
  std::cout << Q << std::endl;
}
