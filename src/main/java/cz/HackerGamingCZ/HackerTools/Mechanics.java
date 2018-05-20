package cz.HackerGamingCZ.HackerTools;

public class Mechanics {

    public float getRealMoveSpeed(final float userSpeed)
    {
        final float defaultSpeed = 0.2F;
        float maxSpeed = 1f;

        if (userSpeed < 1f)
        {
            return defaultSpeed * userSpeed;
        }
        else
        {
            float ratio = ((userSpeed - 1) / 9) * (maxSpeed - defaultSpeed);
            return ratio + defaultSpeed;
        }
    }

}
