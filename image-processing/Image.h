#ifndef IMAGE_H
#define IMAGE_H

#include <string> // Include necessary headers for types used in declarations

class Image {
public:
    // Constructor
    Image(const std::string& filePath);

    // Member function declarations
    std::string getFilePath() const;
    int getWidth() const;
    int getHeight() const;

    void fetchImage();
private:
    std::string filePath;
    int width = 0;
    int height = 0;
    int grey = 0;
    int** pixels = NULL;

    void writePixels(const std::stringstream& ss, const int width, const int height);
};

#endif // IMAGE_H
