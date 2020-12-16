#!/bin/zsh

set -e
set -x

# Create game
gameId=$( curl --fail -s -X POST "http://localhost:8080/games" | sed -e 's/\"//g' )

# Create deck
deckId=$( curl --fail -s -X POST "http://localhost:8080/decks" | sed -e 's/\"//g' )

# Add deck to game
curl --fail -s -X POST "http://localhost:8080/games/$gameId/decks" -H "Content-Type: application/json" --data "\"$deckId\""

# Get card count grouped by suit
curl --fail -s -X GET "http://localhost:8080/games/$gameId/cards-suits"

# Get card count grouped by suit and by value
curl --fail -s -X GET "http://localhost:8080/games/$gameId/cards-suits-values"

# Create deck
deckId=$( curl --fail -s -X POST "http://localhost:8080/decks" | sed -e 's/\"//g' )

# Add deck to game
curl --fail -s -X POST "http://localhost:8080/games/$gameId/decks" -H "Content-Type: application/json" --data "\"$deckId\""

# Get card count grouped by suit and by value
curl --fail -s -X GET "http://localhost:8080/games/$gameId/cards-suits-values"

# Add player to game
playerId=$( curl --fail -s -X POST "http://localhost:8080/games/$gameId/players" | sed -e 's/\"//g' )

# Deal 1 card to player
curl --fail -s -X POST "http://localhost:8080/games/$gameId/players/$playerId/dealCards" -H "Content-Type: application/json" --data "1"

# Get card count grouped by suit and by value
curl --fail -s -X GET "http://localhost:8080/games/$gameId/cards-suits-values"

echo "All passed!"
