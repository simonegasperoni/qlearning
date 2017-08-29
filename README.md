# qlearning, java watkins implementation

The problem consist in a robot (mono agent) that has to find the shortest route to get a specific destination (for example in a domestic environment), the agent (robot) uses unsupervised training to learn an unknown environment.
Therefore we face a typical problem of Reinforcement learning.
I decided to implement two versions of Q-Learning (WATKINS, 1989), a traditional and strengthened to improve convergence with the support of a stack (data structure). In fact we can improve convergence  updating the Q-fuction selecting the state-action transitions in reverse chronological order, for each episode from last state to the first state of the episode; see paragraph 13.3.6 of the book by Mitchell.

See the complete doc into tex file




