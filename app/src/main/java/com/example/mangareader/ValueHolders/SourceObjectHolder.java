package com.example.mangareader.ValueHolders;

import android.app.Activity;
import com.example.mangareader.Settings;
import com.example.mangareader.SourceHandlers.Sources;
import com.example.mangareader.Sources.Mangadex;
import com.example.mangareader.Sources.Mangakakalot;

public class SourceObjectHolder {
    public static Sources sources;

    public static Sources getSources(Activity activity) {
        if (sources != null) {
            return sources;

        } else {
            Settings settings = new Settings();
            String src = settings.ReturnValueString(activity, "source", "mangadex");

            // The default value MUST reflect the default value of the root proferences!!!!
            switch (src) {
                case "mangakakalot":
                    SourceObjectHolder.sources = new Mangakakalot();

                    return sources;

                case "mangadex":
                    SourceObjectHolder.sources = new Mangadex();
                    return sources;

                default:
                    SourceObjectHolder.sources = new Mangadex();
                    return sources;
            }

        }

    }
}
