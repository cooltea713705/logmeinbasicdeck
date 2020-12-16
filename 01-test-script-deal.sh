#!/bin/zsh

set -e
set -x

# Create game
gameId=$( curl --fail -s -X POST "http://localhost:8080/games" | sed -e 's/\"//g' )

# Remove game
curl --fail -s -X DELETE "http://localhost:8080/games/$gameId"

# Create game
gameId=$( curl --fail -s -X POST "http://localhost:8080/games" | sed -e 's/\"//g' )

# Create deck
deckId=$( curl --fail -s -X POST "http://localhost:8080/decks" | sed -e 's/\"//g' )

# Add deck to game
curl --fail -s -X POST "http://localhost:8080/games/$gameId/decks" -H "Content-Type: application/json" --data "\"$deckId\""

# Add player to game
playerId=$( curl --fail -s -X POST "http://localhost:8080/games/$gameId/players" | sed -e 's/\"//g' )

# Deal 32 cards to player
curl --fail -s -X POST "http://localhost:8080/games/$gameId/players/$playerId/dealCards" -H "Content-Type: application/json" --data "32"

# Remove player from game
curl --fail -s -X DELETE "http://localhost:8080/games/$gameId/players/$playerId"

# Add player to game
playerId=$( curl --fail -s -X POST "http://localhost:8080/games/$gameId/players" | sed -e 's/\"//g' )

# Deal 32 cards to player
curl --fail -s -X POST "http://localhost:8080/games/$gameId/players/$playerId/dealCards" -H "Content-Type: application/json" --data "32"

# Retrieve player's cards
curl --fail -s -X GET "http://localhost:8080/games/$gameId/players/$playerId/cards"

# Deal 20 cards to player
curl --fail -s -X POST "http://localhost:8080/games/$gameId/players/$playerId/dealCards" -H "Content-Type: application/json" --data "20"

# Count player's cards (should be 52)
nbCards=$( curl --fail -s -X GET "http://localhost:8080/games/$gameId/players/$playerId/cards" | grep -o '\"uuid\":\"[^\"]*\"' | sort -u | wc -l | sed -e 's/ //g' )

if (( $nbCards != 52 )); then
    exit 1
fi

# Deal 1 more card to player
curl --fail -s -X POST "http://localhost:8080/games/$gameId/players/$playerId/dealCards" -H "Content-Type: application/json" --data "1" | 

# Count player's cards (should be 52 again)
nbCards=$( curl --fail -s -X GET "http://localhost:8080/games/$gameId/players/$playerId/cards" | grep -o '\"uuid\":\"[^\"]*\"' | sort -u | wc -l  | sed -e 's/ //g' )

if (( $nbCards != 52 )); then
    exit 1
fi

# Create deck
deckId=$( curl --fail -s -X POST "http://localhost:8080/decks" | sed -e 's/\"//g' )

# Add deck to game
curl --fail -s -X POST "http://localhost:8080/games/$gameId/decks" -H "Content-Type: application/json" --data "\"$deckId\""

# Deal 1 more card to player
curl --fail -s -X POST "http://localhost:8080/games/$gameId/players/$playerId/dealCards" -H "Content-Type: application/json" --data "1"

# Count player's cards (should be 53)
nbCards=$( curl --fail -s -X GET "http://localhost:8080/games/$gameId/players/$playerId/cards" | grep -o '\"uuid\":\"[^\"]*\"' | sort -u | wc -l | sed -e 's/ //g' )

if (( $nbCards != 53 )); then
    exit 1
fi

# Remove game
curl --fail -s -X DELETE "http://localhost:8080/games/$gameId"

echo "All passed!"
