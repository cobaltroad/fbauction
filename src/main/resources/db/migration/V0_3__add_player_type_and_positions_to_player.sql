ALTER TABLE player
    ADD COLUMN player_type VARCHAR(31),
    ADD COLUMN positions TEXT[],
    ADD COLUMN team TEXT;