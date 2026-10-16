/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {

    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }
    }

    private static TreeNode constructTree(){

        TreeNode root = new TreeNode(5);
        TreeNode nodelevel1_2 = new TreeNode(2);
        TreeNode nodelevel1_9 = new TreeNode(9);

        TreeNode nodelevel2_9 = new TreeNode(9);
        TreeNode nodelevel2_11 = new TreeNode(11);
        nodelevel2_9.right = new TreeNode(9);

        nodelevel1_9.left = nodelevel2_9;
        nodelevel1_9.right = nodelevel2_11;

        nodelevel1_2.right = new TreeNode(4);

        root.left = nodelevel1_2;
        root.right = nodelevel1_9;

        return root;
    }

    private static TreeNode constructTreeWithoutDuplicates(){

        TreeNode root = new TreeNode(5);
        TreeNode nodelevel1_11 = new TreeNode(11);
        nodelevel1_11.left = new TreeNode(6);

        root.left = new TreeNode(4);
        root.right = nodelevel1_11;

        return root;
    }


    public static class TreeDuplicateChecker{

        private int lastNodeValue;
        private boolean foundDuplicateNode;

        public TreeDuplicateChecker(){
            this.lastNodeValue = Integer.MIN_VALUE;
            this.foundDuplicateNode = false;
        }

        private void inOrderTraversal(TreeNode root){

            if(root == null || foundDuplicateNode) return ;

            inOrderTraversal(root.left);

            if(root.val == lastNodeValue) {
                this.foundDuplicateNode = true;
            }
            lastNodeValue = root.val;

            inOrderTraversal(root.right);
        }

        public boolean containDuplicates(TreeNode root){

            inOrderTraversal(root);
            return foundDuplicateNode;
        }




    }

    public static void main(String[] args) {


        TreeNode root = constructTree();
        TreeDuplicateChecker treeDuplicateChecker = new TreeDuplicateChecker();

        System.out.println("foundDuplicateNode : " + treeDuplicateChecker.containDuplicates(root));


        TreeNode root2 = constructTreeWithoutDuplicates();
        TreeDuplicateChecker treeDuplicateCheckerNoDuplicate = new TreeDuplicateChecker();
        System.out.println("foundDuplicateNode : " + treeDuplicateCheckerNoDuplicate.containDuplicates(root2));
    }


}


/*

Your previous Plain Text content is preserved below:

Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

# BST Duplicate Detection

A binary search tree (BST) is a binary tree where, for _every_ node:

- All the values on its **left** subtree are _less than or equal_ to the node's value.
- All the values on its **right** subtree are _greater than or equal_ to the node's value.

Given the root of a binary search tree, determine if it contains any duplicate values.

Example:
              5
            /    \
           2      9
            \    / \
             4  9   11
                 \
                  9
Output: True

Constraints:

- The number of nodes is at most `10^5`
- The height of the tree is at most `500`
- The value at each node is between `0` and `10^9`

 */



/**
 *
 *Welcome to your interview! I've dropped a question for you in the coding environment to the left. Please choose the programming language you'd like to work in from the dropdown.
 *
 * This is NOT meant to feel like LeetCode, and I will NOT be acting as a tutor. Our focus is on mastering the art of interviewing, and the best way to do that is with realistic practice. That means I will not provide you with runnable test cases or method signatures – in a real interview, your interviewer would leave that to you. Discuss your thought process with me in the chat, and ask me questions as needed. I'll give you small "nudges" if you're blocked, and I'll give you feedback once you end the interview.
 *
 * Audio is enabled in this interview. Right now it's one way. You can talk, and I'll respond with text in the chat.
 *
 * #
 * Can you share the question again?
 * BST Duplicate Detection
 * A binary search tree (BST) is a binary tree where, for every node:
 *
 * All the values on its left subtree are less than or equal to the node's value.
 * All the values on its right subtree are greater than or equal to the node's value.
 * Given the root of a binary search tree, determine if it contains any duplicate values.
 *
 * Example: 5 /
 * 2 9 \ /
 * 4 9 11
 * 9 Output: True
 *
 * Constraints:
 *
 * The number of nodes is at most 10^5
 * The height of the tree is at most 500
 * The value at each node is between 0 and 10^9
 * #
 * Can you share the question again?
 * BST Duplicate Detection
 * A binary search tree (BST) is a binary tree where, for every node:
 *
 * All the values on its left subtree are less than or equal to the node's value.
 * All the values on its right subtree are greater than or equal to the node's value.
 * Given the root of a binary search tree, determine if it contains any duplicate values.
 *
 * Example: 5 /
 * 2 9 \ /
 * 4 9 11
 * 9 Output: True
 *
 * Constraints:
 *
 * The number of nodes is at most 10^5
 * The height of the tree is at most 500
 * The value at each node is between 0 and 10^9
 * #
 * hello  hi I am Shubham and I can see the question so the question says hello you are interview question is below ride the code in the just like you normally lose your AI interview will be the questions is BST duplicate detection
 * Hi Shubham! Welcome to the interview.
 *
 * Yes, the question is about BST Duplicate Detection. You're given a binary search tree and you need to determine if it contains any duplicate values.
 *
 * Take a moment to think about the problem, and when you're ready, walk me through your initial thoughts on how you'd approach it.
 *
 * #
 * Soya let me the state the question to make sure understand it correctly so ab binary search tree is a binary search tree binary tree for every node all the values on the left sub less than or equals to the note value and all the values on the rights of tree are greater than or equal to the value to the node value okay  given the route of binary determine if it contain any duplicate values okay
 * That's right. So given a BST with those properties, you need to return True if there are any duplicate values in the tree, and False otherwise.
 *
 * How would you approach solving this problem?
 *
 * #
 *  I use Java to code this up and before coding
 * Sounds good, Java works great. Take your time to think through your approach before jumping into code. What's your initial thinking?
 *
 * #
 *  so in the question we have given a binary search tree and we have to check whether it contains duplicate or not
 * Right, that's the problem. So what approach or strategy would you use to detect those duplicates?
 *
 * #
 * so the one  so we know that the in order Travels of a binary search tree is actually a list sorted list so we can use this property so we can do in order travel sir of a binary search tree and we just check the previous element and for each node we check its previous element if the note value and its previous element are same we return through which means which indicates that there are duplicates in the tree otherwise check for the next Note  so we will use in order and we keep track of the previous note
 * That sounds like a solid approach. An inorder traversal of a BST gives you values in sorted order, so any duplicates would appear as consecutive equal values. Keeping track of the previous value and comparing it with the current node is a clean way to detect that.
 *
 * What would be the time and space complexity of this approach?
 *
 * #
 *  I mean node instead of node
 * Sure, no worries. So what would the time and space complexity be for this inorder traversal approach?
 *
 * #
 * han so kya I think I discuss the core step that is checking the node and its previous value  and the data structure that we are going to uses simply binary search tree which contain left and right pointers to its note and  the time complexity of the solution is Big O of N Where n is the number of notes in the tree because we are doing each node is visited only one time at the space complexity of this solution is order of 1 because we are only using a single variable that is the previous value of Note visited note  visited node
 * The time complexity of O(n) is correct since we visit each node once.
 *
 * For the space complexity though, think about how you're implementing the inorder traversal. What mechanism are you using to traverse the tree, and does that use any additional space?
 *
 * #
 * I will use recursion for in order travel cell of a binary tree and so the space complexity is height of the tree  because we will have like trees of height  so in the statistics we will have like and its number of notes in the stress where h is the height of the tree so the space complexity is order of h  where h is the height of the tree
 * That's correct! The recursive calls use the call stack, and at most you'll have h frames on the stack where h is the height of the tree. So the space complexity is O(h).
 *
 * Go ahead and start coding up your solution.
 *
 * #
 *  height or depth of the three
 * Both terms refer to the same thing in this context — the longest path from the root to a leaf. So O(h) is correct either way.
 *
 * Go ahead and start coding it up!
 *
 * #
 * so I use Java so I am creating a tree note so  it will have like the left node and right node and value
 * I can see you've started defining a TreeNode class in your editor. Just a quick note — on line 27, you have a typo: TeeNode instead of TreeNode. Also on lines 29-30, you're referencing TreeNode for left and right, but the class is named TeeNode. Make sure those are consistent.
 *
 * Go ahead and fix that up and continue with your solution.
 *
 * #
 *  I have created the constructor for the street
 * I see you have the constructor on line 32-34, but there's still that incomplete method on line 36 (public void) — you'll want to either remove that or complete it.
 *
 * Now go ahead and write the main logic for your inorder traversal duplicate detection method.
 *
 * #
 * hello  yeah I have written the code  so I have created two functions construct 3 and another is contains duplicate  which takes a root of the tree and then I have also created a class 3 Note so which have value and left and right pointers of the tree  and so content duplicate sexually does an in order of the tree and it actually updates the last note value as well as found duplicate note  after the an order we actually get the correct value of found duplicate note
 * I can see your code. Let me look at it more carefully.
 *
 * Your containDuplicates method on lines 52-62 does an inorder traversal and compares each node's value with lastNodeValue. The logic looks correct.
 *
 * However, I notice on line 75 you initialize lastNodeValue to -1. Given the constraints that node values can be between 0 and 10^9, could you think about whether -1 is a safe initial value? What would happen if a node in the tree had a value of -1?
 *
 * #
 *  so you can review the code
 * I just pointed out a potential issue. On line 75, you initialize lastNodeValue to -1. The constraints say node values are between 0 and 10^9, so -1 can't actually appear as a node value — but it's still a bit fragile. A safer choice might be to use something like Long.MIN_VALUE or Integer.MIN_VALUE to make it more robust. What do you think?
 *
 * Other than that, your overall logic looks correct. Have you tested it against any edge cases you can think of?
 *
 * #
 * se line 75  both actually works because given the content of the question that the number is between 0 to 10 power 9 so -1 also works but I'll change it to main value
 * Fair point — given the constraints, -1 would work fine. But using Integer.MIN_VALUE is a safer and more defensive practice, so that's a good change.
 *
 * I notice on line 75 you currently have Integer.M — looks like it's incomplete. Make sure you complete that to Integer.MIN_VALUE.
 *
 * Can you think of any edge cases you'd want to test your solution against?
 *
 * #
 * yeah I have changed that and  give you a trace  with a small tree so 54 and
 * Go ahead, walk me through a trace of your solution with a small example.
 *
 * #
 *  so the example that is in the questions so we have created the tree using construct 3 function and then  we call this function construct 3 and then after the clean constructed we call the function contains duplicate so  it takes the route parameter that is 5 and it checks the left sub 3 that is that has no value to and then  it does a two doesn't have left values checks with the last note that is minus min value and it is not equal to like to is not equal to that value so the last note value is to and we check to the content duplicate right note that is 2 is right that is 4 note  and now we are at the phone we check the route is not none and then we check it doesn't have any left or right so we directly check to its value and we say that 4 is not equals to 2 so we check the last note value that is 4 and then we go to the route value of the tree that is 5  and so we  check the route value and 5 is not equals to four update the last note value that is 5 and then we check its we have checked all it's left Sabri and then we go to it rights of tree so the value of the right now is time and then we go to its 9 that is 9 so that 9 has at values 9 so we found the  last note value update the found duplicate note to truth  and so I will give you trace on a very small edge case also
 * Good trace! You walked through the example correctly and showed how the inorder traversal catches the duplicate 9s.
 *
 * What edge cases would you want to test?
 *
 * #
 *  I think would be like when a root value 3 doesn't have any the route is actually null so I think that also work because  the last note values minus one and found duplicate initialised as false so it will return as false and in case root has one node only three containing only in binary search it with one note then also it will work
 * Those are good edge cases — the null tree and single node tree. Both would correctly return false with your implementation.
 *
 * One more thing I want to point out: you're using static variables (lastNodeValue and foundDuplicateNode on lines 49-50) to track state. Can you think of any issues that might cause if this function were called multiple times?
 *
 * #
 *  yeah If multiples are actually calling this function then click multiple threats or multiple processor actually updating this value then it would be a problem I can create a class  that will so we will like the class will contain the tree and it will just check the content duplicate  so shall I go hide with that  go ahead with that
 *
 * hello
 * Yeah, you could encapsulate the state in a class or even just pass a state object into your recursive method. That would avoid the issues with static variables. But for the purposes of this interview, your current solution works correctly for the problem at hand.
 *
 * Overall, I think we've covered the problem well. Let me give you my assessment.
 *
 * Interview Feedback:
 *
 * You would pass this interview. Here's my breakdown:
 *
 * Coding Ability: 3/4 Your code is correct and handles the problem well. The inorder traversal approach is implemented properly. Minor points: the use of static variables isn't ideal, and there were a few small typos during coding, but nothing major.
 *
 * Problem Solving Ability: 3.5/4 You quickly identified the key insight — that an inorder traversal of a BST produces sorted values, making duplicate detection a matter of comparing consecutive elements. This is the optimal solution with O(n) time and O(h) space.
 *
 * Communication Skills: 3/4 You clearly stated your approach before coding, explained the time and space complexity correctly (after a small nudge on space complexity), and walked through a trace of the example. Your edge case analysis was solid. Could improve slightly by being a bit more concise and structured in your explanations.
 *
 * Great job, Shubham!
 *
 * #
 *  so what is the verdict of the interview
 * As I mentioned, you would pass this interview! 🎉
 *
 * Here's a quick summary of your scores:
 *
 * Category	Score
 * Coding Ability	3/4
 * Problem Solving	3.5/4
 * Communication	3/4
 * Strengths:
 *
 * You identified the optimal approach (inorder traversal) right away without any hints
 * Correct time complexity O(n) and space complexity O(h)
 * Good edge case analysis (null tree, single node)
 * You coded a working solution
 * Areas for improvement:
 *
 * Avoid using static/global variables for state — encapsulating state in an object is cleaner
 * Be a bit more concise and structured when explaining your thought process
 * Initially missed the call stack space in your space complexity analysis, but corrected it quickly
 * Overall, a solid performance. Well done, Shubham!
 *
 * #
 *  also like before ending the interview based on today's interview Can you provide some feedback for the next interview
 * Great question! Here are some tips for your next interview:
 *
 * 1. Structure your explanations more clearly Try following a consistent pattern: (1) Restate the problem, (2) State your approach in 2-3 sentences, (3) Discuss complexity, (4) Code, (5) Test. Today your explanations were a bit scattered at times — being more structured will make a stronger impression.
 *
 * 2. Think about space complexity upfront You initially said O(1) space before catching the recursion stack. Make it a habit to always consider whether your solution uses recursion or any implicit data structures when analyzing space.
 *
 * 3. Avoid static/global state In interviews, using global or static variables can raise concerns about code quality. Practice passing state through method parameters or encapsulating it in an object. It shows stronger software engineering instincts.
 *
 * 4. Test before declaring you're done Before saying your code is complete, quickly run through at least 2-3 test cases mentally — the given example, an edge case, and a negative case (no duplicates). Today you did this when I prompted you, but ideally you'd do it proactively.
 *
 * 5. Consider alternative approaches Even if your first approach is optimal, briefly mentioning other approaches (like passing min/max values up the tree) shows breadth of thinking and can impress interviewers.
 *
 * 6. Be concise Practice explaining your ideas in fewer words. Interviewers appreciate clarity and brevity — it shows confidence and strong communication.
 *
 * Keep practicing, and you'll do great in
 */