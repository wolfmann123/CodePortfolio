package com.example.libraryview;

import java.util.ArrayList;

public class Books {
    private String mTitle;
    private boolean mDownloaded;

    public Books(String name, boolean downloaded) {
        mTitle = name;
        mDownloaded = downloaded;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isDownloaded() {
        return mDownloaded;
    }

    public void setDownloaded(boolean status) {
        mDownloaded = status;
    }

    public static ArrayList<Books> createBooksList(int numBooks) {
        ArrayList<Books> book = new ArrayList<>();
        String[] bookTitle = new String[] {
                "The Time Machine by H. G.  Wells",
                "A Christmas Carol by Charles Dickens",
                "Adventures of Huckleberry Finn by Mark Twain" ,
                "The Adventures of Sherlock Holmes by Arthur Conan Doyle" ,
                "The Adventures of Tom Sawyer by Mark Twain" ,
                "Alice's Adventures in Wonderland by Lewis Carroll" ,
                "Also sprach Zarathustra. English by Friedrich Wilhelm Nietzsche" ,
                "An Occurrence at Owl Creek Bridge by Ambrose Bierce" ,
                "Anne of Green Gables by L. M.  Montgomery" ,
                "Anthem by Ayn Rand" ,
                "Autobiography of Benjamin Franklin by Benjamin Franklin" ,
                "The Awakening, and Selected Short Stories by Kate Chopin" ,
                "Beowulf: An Anglo-Saxon Epic Poem by " ,
                "Beyond Good and Evil by Friedrich Wilhelm Nietzsche" ,
                "The Brothers Karamazov by Fyodor Dostoyevsky" ,
                "Candide by Voltaire" ,
                "The Chaldean Account of Genesis by A. H.  Sayce and George Smith" ,
                "A Christmas Carol in Prose; Being a Ghost Story of Christmas by Charles Dickens" ,
                "Common Sense by Thomas Paine" ,
                "The Complete Works of William Shakespeare by William Shakespeare" ,
                "The Confessions of St. Augustine by Bishop of Hippo Saint Augustine" ,
                "The Count of Monte Cristo by Alexandre Dumas" ,
                "Crime and Punishment by Fyodor Dostoyevsky" ,
                "The Criticism of the Fourth Gospel by W.  Sanday" ,
                "Democracy in America — Volume 1 by Alexis de Tocqueville" ,
                "The Devil's Dictionary by Ambrose Bierce" ,
                "Divine Comedy, Longfellow's Translation, Hell by Dante Alighieri" ,
                "A Doll's House: a play by Henrik Ibsen" ,
                "Dracula by Bram Stoker" ,
                "Dubliners by James Joyce" ,
                "Emma by Jane Austen" ,
                "Essays by Ralph Waldo Emerson by Ralph Waldo Emerson" ,
                "Essays of Michel de Montaigne — Complete by Michel de Montaigne" ,
                "Ethan Frome by Edith Wharton" ,
                "Frankenstein; Or, The Modern Prometheus by Mary Wollstonecraft Shelley" ,
                "Great Expectations by Charles Dickens" ,
                "Grimms' Fairy Tales by Jacob Grimm and Wilhelm Grimm" ,
                "Gulliver's Travels into Several Remote Nations of the World by Jonathan Swift" ,
                "Heart of Darkness by Joseph Conrad" ,
                "The Hound of the Baskervilles by Arthur Conan Doyle" ,
                "How the Other Half Lives: Studies Among the Tenements of New York by Jacob A.  Riis" ,
                "The Iliad by Homer" ,
                "The Importance of Being Earnest: A Trivial Comedy for Serious People by Oscar Wilde" ,
                "Incidents in the Life of a Slave Girl, Written by Herself by Harriet A.  Jacobs" ,
                "The Interesting Narrative of the Life of Olaudah Equiano, Or Gustavus Vassa, The African by Equiano" ,
                "Ion by Plato" ,
                "Jane Eyre: An Autobiography by Charlotte Brontë" ,
                "The Jungle by Upton Sinclair" ,
                "The Kama Sutra of Vatsyayana by Vatsyayana" ,
                "Le Morte d'Arthur: Volume 1 by Sir Thomas Malory" ,
                "Leaves of Grass by Walt Whitman" ,
                "The Legend of Sleepy Hollow by Washington Irving" ,
                "Les Misérables by Victor Hugo" ,
                "Leviathan by Thomas Hobbes" ,
                "The Life and Adventures of Robinson Crusoe by Daniel Defoe" ,
                "Little Women by Louisa May Alcott" ,
                "Metamorphosis by Franz Kafka" ,
                "Moby Dick; Or, The Whale by Herman Melville" ,
                "Modern Copper Smelting by Donald M. Levy" ,
                "A Modest Proposal by Jonathan Swift" ,
                "Narrative of the Captivity and Restoration of Mrs. Mary Rowlandson by Mary White Rowlandson" ,
                "Narrative of the Life of Frederick Douglass, an American Slave by Frederick Douglass" ,
                "On Liberty by John Stuart Mill" ,
                "The Parochial History of Cornwall, Volume 1 by " ,
                "Peter Pan by J. M.  Barrie" ,
                "The Picture of Dorian Gray by Oscar Wilde" ,
                "Pride and Prejudice by Jane Austen" ,
                "The Prince by Niccolò Machiavelli" ,
                "The Problems of Philosophy by Bertrand Russell" ,
                "The Prophet by Kahlil Gibran" ,
                "Pygmalion by Bernard Shaw" ,
                "The Republic by Plato" ,
                "The Scarlet Letter by Nathaniel Hawthorne" ,
                "Second Treatise of Government by John Locke" ,
                "Sense and Sensibility by Jane Austen" ,
                "Siddhartha by Hermann Hesse" ,
                "Songs of Innocence, and Songs of Experience by William Blake" ,
                "The Souls of Black Folk by W. E. B.  Du Bois" ,
                "The Strange Case of Dr. Jekyll and Mr. Hyde by Robert Louis Stevenson" ,
                "A Study in Scarlet by Arthur Conan Doyle" ,
                "A Tale of Two Cities by Charles Dickens" ,
                "The Theory of the Leisure Class by Thorstein Veblen" ,
                "The Time Machine by H. G.  Wells" ,
                "The Tragical History of Doctor Faustus by Christopher Marlowe" ,
                "Treasure Island by Robert Louis Stevenson" ,
                "The Turn of the Screw by Henry James" ,
                "Ulysses by James Joyce" ,
                "Uncle Tom's Cabin by Harriet Beecher Stowe" ,
                "The Used People Lot by Irving E. Fang" ,
                "Walden, and On The Duty Of Civil Disobedience by Henry David Thoreau" ,
                "War and Peace by graf Leo Tolstoy" ,
                "The War of the Worlds by H. G.  Wells" ,
                "The Wonderful Wizard of Oz by L. Frank  Baum" ,
                "The Works of Edgar Allan Poe — Volume 2 by Edgar Allan Poe" ,
                "The Works of Edgar Allan Poe, The Raven Edition by Edgar Allan Poe" ,
                "Wuthering Heights by Emily Brontë" ,
                "The Yellow Wallpaper by Charlotte Perkins Gilman"
        };
        for (int i = 0; i < bookTitle.length; i++) {
            book.add(new Books(""+bookTitle[i], true));
        }

        return book;
    }
}
