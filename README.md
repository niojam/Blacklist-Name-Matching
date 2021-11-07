## Blacklist Name Matching

### Set up

Just import project as gradle project. (Java 11 is required)

You can check the code logic by executing tests which are located in ```src/test/java``` folder. 
Test files are located in ```src/test/resources``` folder. 

### Explanation

```class BlacklistChecker``` has method ```isNameInBlackList``` which takes 3 arguments:
1. Name to validate against blacklist
2. Input file name that contains one blacklisted name per line
3. Input file name that contains one noise word per line

To provide better accuracy there are used 2 main strategies at the same time: 
    
1). DamerauLevenshteinDistanceCalculator  - edit distance between two strings is the number of deletions, insertions, or substitutions required to transform source string into target string
    
2). Soundex - phonetic algorithm for indexing names by sound


