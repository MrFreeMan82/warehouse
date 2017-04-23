package com.warehouse.client.present;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 23.04.2017.
 *
 */

public class TreeSample
{
        /**
         * A list of songs.
         */
        static class Playlist
        {
            private final String name;
            private final List<String> songs = new ArrayList<>();

            Playlist(String name) {  this.name = name;  }

            void addSong(String name) {  songs.add(name); }
            String getName() {  return name;  }
            List<String> getSongs() { return songs; }
        }

    static class Composer {
        private final String name;
        private final List<Playlist> playlists = new ArrayList<>();

        Composer(String name) { this.name = name; }

        Playlist addPlaylist(Playlist playlist) {
            playlists.add(playlist);
            return playlist;
        }

        String getName() { return name; }

        List<Playlist> getPlaylists() { return playlists; }
    }

    static class CustomTreeModel implements TreeViewModel
    {
        private final List<Composer> composers;
        private final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<>();

        CustomTreeModel()
        {
            // Create a database of information.
            composers = new ArrayList<>();

            // Add compositions by Beethoven.
            {
                Composer beethoven = new Composer("Beethoven");
                composers.add(beethoven);

                Playlist concertos = beethoven.addPlaylist(new Playlist("Concertos"));
                concertos.addSong("No. 1 - C");
                concertos.addSong("No. 2 - B-Flat Major");
                concertos.addSong("No. 3 - C Minor");
                concertos.addSong("No. 4 - G Major");
                concertos.addSong("No. 5 - E-Flat Major");

                Playlist quartets = beethoven.addPlaylist(new Playlist("Quartets"));
                quartets.addSong("Six String Quartets");
                quartets.addSong("Three String Quartets");
                quartets.addSong("Grosse Fugue for String Quartets");

                Playlist sonatas = beethoven.addPlaylist(new Playlist("Sonatas"));
                sonatas.addSong("Sonata in A Minor");
                sonatas.addSong("Sonata in F Major");

                Playlist symphonies = beethoven.addPlaylist(new Playlist("Symphonies"));
                symphonies.addSong("No. 2 - D Major");
                symphonies.addSong("No. 2 - D Major");
                symphonies.addSong("No. 3 - E-Flat Major");
                symphonies.addSong("No. 4 - B-Flat Major");
                symphonies.addSong("No. 5 - C Minor");
                symphonies.addSong("No. 6 - F Major");
                symphonies.addSong("No. 7 - A Major");
                symphonies.addSong("No. 8 - F Major");
                symphonies.addSong("No. 9 - D Minor");
            }

            // Add compositions by Brahms.
            {
                Composer brahms = new Composer("Brahms");
                composers.add(brahms);
                Playlist concertos = brahms.addPlaylist(new Playlist("Concertos"));
                concertos.addSong("Violin Concerto");
                concertos.addSong("Double Concerto - A Minor");
                concertos.addSong("Piano Concerto No. 1 - D Minor");
                concertos.addSong("Piano Concerto No. 2 - B-Flat Major");

                Playlist quartets = brahms.addPlaylist(new Playlist("Quartets"));
                quartets.addSong("Piano Quartet No. 1 - G Minor");
                quartets.addSong("Piano Quartet No. 2 - A Major");
                quartets.addSong("Piano Quartet No. 3 - C Minor");
                quartets.addSong("String Quartet No. 3 - B-Flat Minor");

                Playlist sonatas = brahms.addPlaylist(new Playlist("Sonatas"));
                sonatas.addSong("Two Sonatas for Clarinet - F Minor");
                sonatas.addSong("Two Sonatas for Clarinet - E-Flat Major");

                Playlist symphonies = brahms.addPlaylist(new Playlist("Symphonies"));
                symphonies.addSong("No. 1 - C Minor");
                symphonies.addSong("No. 2 - D Minor");
                symphonies.addSong("No. 3 - F Major");
                symphonies.addSong("No. 4 - E Minor");
            }

            // Add compositions by Mozart.
            {
                Composer mozart = new Composer("Mozart");
                composers.add(mozart);
                Playlist concertos = mozart.addPlaylist(new Playlist("Concertos"));
                concertos.addSong("Piano Concerto No. 12");
                concertos.addSong("Piano Concerto No. 17");
                concertos.addSong("Clarinet Concerto");
                concertos.addSong("Violin Concerto No. 5");
                concertos.addSong("Violin Concerto No. 4");
            }
        }

        public <T> TreeViewModel.NodeInfo<?> getNodeInfo(T value)
        {
            if (value == null) {
                // LEVEL 0.
                ListDataProvider<Composer> dataProvider = new ListDataProvider<>(composers);

                Cell<Composer> cell = new AbstractCell<Composer>()
                {
                    @Override
                    public void render(Context context, Composer value, SafeHtmlBuilder sb)
                    {
                        if (value != null) {
                            sb.appendEscaped(value.getName());
                        }
                    }
                };
                return new DefaultNodeInfo<>(dataProvider, cell);

            } else if (value instanceof Composer) {
                // LEVEL 1.
                ListDataProvider<Playlist> dataProvider = new ListDataProvider<>(((Composer) value).getPlaylists());

                Cell<Playlist> cell = new AbstractCell<Playlist>()
                {
                    @Override
                    public void render(Context context, Playlist value, SafeHtmlBuilder sb)
                    {
                        if (value != null) {
                            sb.appendEscaped(value.getName());
                        }
                    }
                };
                return new DefaultNodeInfo<>(dataProvider, cell);

            } else if (value instanceof Playlist) {
                // LEVEL 2 - LEAF.

                ListDataProvider<String> dataProvider = new ListDataProvider<>(((Playlist) value).getSongs());

                // Use the shared selection model.
                return new DefaultNodeInfo<>(dataProvider, new TextCell(), selectionModel, null);
            }
            return null;
        }

        /**
         * Check if the specified value represents a leaf node. Leaf nodes cannot be
         * opened.
         */
        public boolean isLeaf(Object value) {
            // The leaf nodes are the songs, which are Strings.
            return (value instanceof String);
        }

    }
}
