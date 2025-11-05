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
    int width;
    int height;
    int** pixels;
};

#endif // IMAGE_H
