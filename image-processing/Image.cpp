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

/**
 * Fetchs image from the path in this->filePath
 */
void Image::fetchImage()
{
  std::ifstream fetchedFile(filePath, std::ios::in | std::ios::binary);
  if (!fetchedFile.is_open()) {
    std::cout << "Error opening " << filePath << std::endl;
  }
  std::stringstream ss;
  std::string inputLine = "";

  std::getline(fetchedFile, inputLine); // Reads first line containing version the eg 'P5', 'P3'
  std::cout << "Version: " << inputLine << std::endl;

  // Some pgm files (like the ones created with gimp) may contain comments on the second line
  // std::getline(fetchedFile, inputLine);
  // std::cout << "Comment: " << inputLine << std::endl;

  ss << fetchedFile.rdbuf(); // Reads second line 'width height' 
  ss >> this->width;
  ss >> this->height;
  ss >> this->grey; // buffers next line which contains the grey value of the image
  ss.ignore();

  std::cout << this->width << " columns and " << this->height << " rows" << std::endl;
  int Q = 0;
  std::cout << Q << std::endl;

  //this->writePixels(ss, this->width, this->height);
  std::cout << "pixel 0: " << this->pixels[0][0]; << std::endl;

  unsigned int pixel = 0;
  this->pixels = new int[*this->width][*this->height]; // Initializes pixel 2d array

  for (int w = 0; w < *this->width; w++) {
    for (int h = 0; h < *this->height; h++) {
      ss >> pixel;
      pixels[w][h] = pixel;
    }
  }
}

void Image::writePixels(const std::stringstream& ss, const int width, const int height) {
}
