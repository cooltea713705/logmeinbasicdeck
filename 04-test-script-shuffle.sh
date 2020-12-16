#!/bin/zsh

set -e
set -x

# Create game
gameId=$( curl --fail -s -X POST "http://localhost:8080/games" | sed -e 's/\"//g' )

# Create deck
deckId=$( curl --fail -s -X POST "http://localhost:8080/decks" | sed -e 's/\"//g' )

# Add deck to game
curl --fail -s -X POST "http://localhost:8080/games/$gameId/decks" -H "Content-Type: application/json" --data "\"$deckId\""

# Add player to game
playerId=$( curl --fail -s -X POST "http://localhost:8080/games/$gameId/players" | sed -e 's/\"//g' )

# Shuffle
shuffleId=$( curl --fail -s -X POST "http://localhost:8080/games/$gameId/shuffles" | sed -e 's/\"//g' )

# Deal 42 cards to player (to allow seeing the shuffle better)
curl --fail -s -X POST "http://localhost:8080/games/$gameId/players/$playerId/dealCards" -H "Content-Type: application/json" --data "42"

# Get game deck
curl --fail -s -X GET "http://localhost:8080/games/$gameId/cards"

# Shuffle
shuffleId=$( curl --fail -s -X POST "http://localhost:8080/games/$gameId/shuffles" | sed -e 's/\"//g' )

# Get game deck
curl --fail -s -X GET "http://localhost:8080/games/$gameId/cards"

echo "All passed!"
