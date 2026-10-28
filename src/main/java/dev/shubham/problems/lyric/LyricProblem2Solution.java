package dev.shubham.problems.lyric;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LyricProblem2Solution {

    private static final int SHELF_CAPACITY = 20;

    private static int insert(int[][] bookcase, int width, int index) {
        List<List<Integer>> shelves = new ArrayList<>();
        int totalBooks = 0;
        for (int[] shelf : bookcase) {
            List<Integer> shelfList = new ArrayList<>();
            for (int book : shelf) {
                shelfList.add(book);
                totalBooks++;
            }
            shelves.add(shelfList);
        }

        if (index < 0 || index > totalBooks) {
            return -1;
        }

        int currentGlobalIndex = 0;
        int targetShelfIdx = -1;
        int targetPosInShelf = -1;

        for (int i = 0; i < shelves.size(); i++) {
            int shelfLen = shelves.get(i).size();
            if (index < currentGlobalIndex + shelfLen) {
                targetShelfIdx = i;
                targetPosInShelf = index - currentGlobalIndex;
                break;
            }
            currentGlobalIndex += shelfLen;
        }

        if (targetShelfIdx == -1) {
            // index landed exactly at the end of the last shelf (append).
            if (index == totalBooks && !shelves.isEmpty()) {
                targetShelfIdx = shelves.size() - 1;
                targetPosInShelf = shelves.get(targetShelfIdx).size();
            } else {
                return -1;
            }
        }

        List<Integer> targetShelf = shelves.get(targetShelfIdx);
        targetShelf.add(targetPosInShelf, width);
        int newBookPos = targetPosInShelf;

        int totalBooksMoved = 0;

        // The newly inserted book never moves — only the pre-existing books now sitting
        // to its right on this shelf are eligible to cascade down.
        while (shelfWidth(targetShelf) > SHELF_CAPACITY) {
            if (targetShelf.size() - 1 == newBookPos || targetShelfIdx + 1 >= shelves.size()) {
                return -1;
            }
            int removedBook = targetShelf.remove(targetShelf.size() - 1);
            totalBooksMoved++;
            shelves.get(targetShelfIdx + 1).add(0, removedBook);
        }

        int currShelf = targetShelfIdx + 1;
        while (currShelf < shelves.size() && shelfWidth(shelves.get(currShelf)) > SHELF_CAPACITY) {
            List<Integer> shelf = shelves.get(currShelf);
            while (shelfWidth(shelf) > SHELF_CAPACITY) {
                if (currShelf + 1 >= shelves.size()) {
                    return -1;
                }
                int removedBook = shelf.remove(shelf.size() - 1);
                totalBooksMoved++;
                shelves.get(currShelf + 1).add(0, removedBook);
            }
            currShelf++;
        }

        return totalBooksMoved;
    }

    private static int shelfWidth(List<Integer> shelf) {
        int sum = 0;
        for (int w : shelf) {
            sum += w;
        }
        return sum;
    }

    public static void main(String[] argv) {
        int[][] bookcase1 = {
            {2, 2, 3, 5, 3, 1, 3},
            {1, 5, 5, 5, 1, 1, 1},
            {3, 3, 3, 1, 1, 1}
        };

        int[][] bookcase2 = {
            {1, 4, 3, 2, 3},
            {5, 3, 1, 2},
            {4},
            {3, 3, 1, 2, 3, 5},
            {},
            {5, 3, 1, 3}
        };

        int[][] bookcase3 = {
            {10, 5, 1, 4},
            {9, 5, 5, 1},
            {10, 4, 1, 1},
            {1, 2, 3}
        };

        assertEquals(3, insert(copy(bookcase1), 2, 2));
        assertEquals(3, insert(copy(bookcase1), 4, 11));
        assertEquals(0, insert(copy(bookcase1), 1, 0));
        assertEquals(1, insert(copy(bookcase1), 2, 9));
        assertEquals(-1, insert(copy(bookcase1), 10, 9));
        assertEquals(-1, insert(copy(bookcase1), 5, 11));
        assertEquals(-1, insert(copy(bookcase1), 3, 13));
        assertEquals(1, insert(copy(bookcase2), 4, 15));
        assertEquals(0, insert(copy(bookcase2), 4, 16));
        assertEquals(5, insert(copy(bookcase3), 4, 3));

        System.out.println("All LyricProblem2Solution test cases passed!");
    }

    private static int[][] copy(int[][] bookcase) {
        int[][] result = new int[bookcase.length][];
        for (int i = 0; i < bookcase.length; i++) {
            result[i] = bookcase[i].clone();
        }
        return result;
    }
}
