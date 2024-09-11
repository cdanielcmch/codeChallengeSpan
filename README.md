# Code Challenge Span

Welcome to the Code Challenge Span repository. This project implements a league ranking system that reads match results from a file, computes scores for teams, sorts them, and prints the results. The system is built using Java and includes several utilities to manage team rankings effectively.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Requirements](#requirements)
- [Usage](#usage)

## Overview

The League Ranking System in this repository performs the following:

- **Read and Parse Match Results**: Extracts team names and scores from a file.
- **Score Calculation**: Calculates points based on match results.
- **Sorting**: Teams are sorted by score (descending) and name (alphabetical) in case of ties.
- **Display**: Outputs the sorted list of teams with their ranks and scores.

## Features

- **Read and Parse Match Results**: Handles match results input to extract and process team scores.
- **Score Calculation**: Awards points based on match outcomes:
  - Win: 3 points
  - Draw: 1 point
  - Loss: 0 points
- **Sorting**: Sorts teams by scores and names.
- **Display**: Prints the ranked teams.

## Requirements

- **Java 11** or higher
- **JUnit 4** for testing

## Usage
**Ensure that test files are located in the src/test/resources directory. These files are essential for testing.**
1. **Reading Match Results**

   Use `FileHelper.readFile(String fileName)` to read and parse match results from a file.

   ```java
   Map<String, Integer> scores = FileHelper.readFile("test/resources/testFile.txt");

2. **Sorting Teams**

   Use `FileHelper.sortByValue(Map<String, Integer> scores)` to get a sorted list of Team objects.

   ```java
   List<Team> sortedTeams = FileHelper.sortByValue(scores);

3. **Printing Teams**

   Call `FileHelper.printTeamsWithRanks(List<Team> teams)` to print the sorted teams with their ranks.

   ```java
   FileHelper.printTeamsWithRanks(sortedTeams);
