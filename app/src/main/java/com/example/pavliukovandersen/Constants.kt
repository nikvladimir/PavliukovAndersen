package com.example.pavliukovandersen

class Constants {
    companion object {
        // DBHelper for playlist
        const val DB_NAME = "playlist"
        const val PL_TABLE_NAME = "Userdata"
        private const val PL_COLUMN_1 = "artist"  // artist
        private const val PL_COLUMN_2 = "song_name"  // song_name
        private const val PL_COLUMN_3 = "genre"
        private const val PL_COLUMN_4 = "file_name"

        const val CREATE_PL_TABLE = "CREATE TABLE IF NOT EXISTS $PL_TABLE_NAME (" +
                "$PL_COLUMN_1 TEXT, " +
                "$PL_COLUMN_2 TEXT, " +
                "$PL_COLUMN_3 TEXT, " +
                "$PL_COLUMN_4 TEXT" +
                ")"
        const val DROP_PL_TABLE = "DROP TABLE IF EXISTS $PL_TABLE_NAME"
        const val SELECT_ALL_FROM_PL = "SELECT * FROM $PL_TABLE_NAME"
        const val SELECT_ARTIST = "SELECT DISTINCT $PL_COLUMN_1 FROM $PL_TABLE_NAME"
        const val SELECT_GENRE = "SELECT DISTINCT $PL_COLUMN_3 FROM $PL_TABLE_NAME"

        const val FILLING_THE_PL_TABLE = "INSERT INTO $PL_TABLE_NAME " +
                "($PL_COLUMN_1, $PL_COLUMN_2, $PL_COLUMN_3, $PL_COLUMN_4) VALUES " +
                "('Rammstein', 'Links-2-3-4', 'Metal', 'rammstein_links_2_3_4.mp3'), " +
                "('Rammstein', 'Du hast', 'Metal', 'rammstein_du_hast.mp3'), " +
                "('Rammstein', 'Feuer-frei', 'Metal', 'rammstein_feuer_frei.mp3'), " +
                "('Ария', 'Возьми моё сердце', 'Metal', 'arija_vozmi_mojo_serdce.mp3'), " +
                "('Ария', 'Грязь', 'Metal', 'arija_grjaz.mp3'), " +
                "('Король и шут', 'Кукла колдуна', 'Rock', 'korol_i_shut_kukla_kolduna.mp3'), " +
                "('Король и шут', 'Лесник', 'Rock', 'korol_i_shut_lesnik.mp3'), " +
                "('Король и шут', 'Два друга', 'Rock', 'korol_i_shut_dva_druga.mp3'), " +
                "('Король и шут', 'Валет и дама', 'Rock', 'korol_i_shut_valet_i_dama.mp3'), " +
                "('Alex Clare', 'Too close', 'Pop', 'alex_clare_too_close.mp3'), " +
                "('Eminem', 'Lose yourself', 'Pop', 'eminem_lose_yourself.mp3');"
    }
}