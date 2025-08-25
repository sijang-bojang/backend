-- Add complete_stamp column to users table
-- This script adds the complete_stamp column for tracking user's completed course stamps

ALTER TABLE users ADD COLUMN complete_stamp INT DEFAULT 0;

-- Update existing users to have 0 complete stamps
UPDATE users SET complete_stamp = 0 WHERE complete_stamp IS NULL;
