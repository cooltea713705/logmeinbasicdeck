#!/bin/zsh

set -e
set -x

# Create game
gameId=$( curl --fail -s -X POST "http://localhost:8080/games" | sed -e 's/\"//g' )

# Create deck
deckId=$( curl --fail -s -X POST "http://localhost:8080/decks" | sed -e 's/\"//g' )

# Add deck to game
curl --fail -s -X POST "http://localhost:8080/games/$gameId/decks" -H "Content-Type: application/json" --data "\"$deckId\""

# Add player 1 to game
playerId1=$( curl --fail -s -X POST "http://localhost:8080/games/$gameId/players" | sed -e 's/\"//g' )

# Add player 2 to game
playerId2=$( curl --fail -s -X POST "http://localhost:8080/games/$gameId/players" | sed -e 's/\"//g' )

# Add player 3 to game
playerId3=$( curl --fail -s -X POST "http://localhost:8080/games/$gameId/players" | sed -e 's/\"//g' )

# Deal 10 cards to player 1
curl --fail -s -X POST "http://localhost:8080/games/$gameId/players/$playerId1/dealCards" -H "Content-Type: application/json" --data "10"

# Deal 10 cards to player 2
curl --fail -s -X POST "http://localhost:8080/games/$gameId/players/$playerId2/dealCards" -H "Content-Type: application/json" --data "10"

# Deal 10 cards to player 2
curl --fail -s -X POST "http://localhost:8080/games/$gameId/players/$playerId3/dealCards" -H "Content-Type: application/json" --data "10"

# Retrieve players and their card values in descending order
curl --fail -s -X GET "http://localhost:8080/games/$gameId/players"

# TODO assertion

echo "All passed!"
