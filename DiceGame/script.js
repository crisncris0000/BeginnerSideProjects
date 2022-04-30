'use strict';

const player0El = document.querySelector(".player--0");
const player1El = document.querySelector(".player--1");
const score0El = document.querySelector("#score--0");
const score1El = document.getElementById("score--1");
const current0El = document.getElementById("current--0");
const current1El = document.getElementById("current--1");

const diceEl = document.querySelector(".dice");
const btnNew = document.querySelector(".btn--new");
const btnRoll = document.querySelector(".btn--roll");
const btnHold = document.querySelector(".btn--hold");
const btnNewGame = document.querySelector(".btn--new");


score0El.textContent = 0;
score1El.textContent = 0;
diceEl.classList.add("hidden");

let scores = [0, 0];
let currentScore = 0;
let activePlayer = 0;
let playing = true;

const switchPlayer = function()
{
     //Switch next player
     document.getElementById(`current--${activePlayer}`).textContent = 0;
     currentScore = 0;
     activePlayer = activePlayer === 0 ? 1 : 0; 
     player0El.classList.toggle("player--active"); //if its not there add it if its there remove it
     player1El.classList.toggle("player--active");
}

// Rolling dice function
btnRoll.addEventListener("click", function()
{
    if(playing){
// 1. Generating a random dice roll
const dice = Math.trunc(Math.random() * 6) + 1;

// 2. Display dice
diceEl.classList.remove("hidden");
diceEl.src = `dice-${dice}.png`; // Remember to use `` marks, not "" or ''


// 3. Check for rolled 1: if true, switch to next player
if(dice !== 1)
{ //Add dice to current score
 currentScore += dice;
 document.getElementById(`current--${activePlayer}`).textContent = currentScore;



}else
{
   switchPlayer();
}
}
});


btnHold.addEventListener("click", function()
{
    if(playing){
//1. add current score to active player's score

scores[activePlayer] += currentScore;

document.getElementById(`score--${activePlayer}`).textContent = scores[activePlayer];


//2. Check if player's score is >=100
//Finish the game
if(scores[activePlayer] >= 10)
{
    playing = false;

    
    document.querySelector(`.player--${activePlayer}`).classList.add("player--winner");
    document.querySelector(`.player--${activePlayer}`).classList.remove("player--active");
    diceEl.classList.add("hidden");
   
// Reset Game
    btnNewGame.addEventListener("click", function()
{
    activePlayer = 0;
 
    player1El.classList.remove("player--winner");
    diceEl.classList.add("hidden");
    currentScore = 0;
    current0El.textContent = 0;
    current1El.textContent = 0;
    document.querySelector(`.player--${activePlayer}`).classList.remove("player--winner");
    player0El.classList.add("player--active");
    player1El.classList.remove("player--active");
    playing = true;
    score0El.textContent = 0;
    score1El.textContent = 0;
    scores[0] = 0;
    scores[1] = 0;
    console.log(score0El.textContent, );

})

}
else
{
    switchPlayer();
}
    }
});


