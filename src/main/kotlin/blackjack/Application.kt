package blackjack

fun main() {
    val players = Players(InputView.inputPlayers())
    OutputView.showPlayersCard(players.getPlayers())
    playGame(players.getPlayers())
    OutputView.getWinner(players.getPlayers())
}

fun playGame(players: List<Player>) {
    players.forEach {
        val score = it.getCards().sumCardNumbers()
        if (score > Cards.WIN_SCORE) return
        addUserCards(it)
    }
}

private fun addUserCards(player: Player) {
    loop@ while (true) {
        try {
            val selectedValue = InputView.selectMoreCard(player.getName())
            if (player.addMoreCards(selectedValue, Card.getInstances())) break@loop
            OutputView.getUserCard(player)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            break@loop
        }
    }
}
