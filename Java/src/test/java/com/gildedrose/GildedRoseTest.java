package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//https://github.com/emilybache/GildedRose-Refactoring-Kata/blob/main/GildedRoseRequirements.md

@Nested
@DisplayName("#GildedRose")
class GildedRoseTest {

    @Nested
    @DisplayName("#updateQuality")
    class UpdateQuality {

        // The Quality of an item is never more than 50
        @DisplayName("quality can be more than 50")
        @Test
        void qualityCanBeMoreThanFifty() {
            // Given
            Item item = new Item("item", 1, 52);
            GildedRose application = new GildedRose(new Item[]{item});

            // When
            application.updateQuality();

            // Then
            Item actual = application.items[0];
            assertEquals(51, actual.quality);
        }

        @Nested
        @DisplayName("if is not special")
        class NotSpecial {

            @Nested
            @DisplayName("and sells in 1 day, and its quality is 1")
            class SellsInOneDay {

                @DisplayName("name should not be modified")
                @Test
                void nameNotModified() {
                    // Given
                    Item item = new Item("item", 1, 1);
                    GildedRose application = new GildedRose(new Item[]{item});

                    // When
                    application.updateQuality();

                    // Then
                    Item actual = application.items[0];
                    assertEquals("item", actual.name);
                }

                @DisplayName("sellIn should be zero")
                @Test
                void sellIn() {
                    // Given
                    Item item = new Item("item", 1, 1);
                    GildedRose application = new GildedRose(new Item[]{item});

                    // When
                    application.updateQuality();

                    // Then
                    Item actual = application.items[0];
                    assertEquals(0, actual.sellIn);
                }

                @DisplayName("quality should be zero")
                @Test
                void qualityZero() {
                    // Given
                    Item item = new Item("item", 1, 1);
                    GildedRose application = new GildedRose(new Item[]{item});

                    // When
                    application.updateQuality();

                    // Then
                    Item actual = application.items[0];
                    assertEquals(0, actual.quality);
                }
            }

            @Nested
            @DisplayName("if item is expired")
            class Expired {

                // Once the sell by date has passed, Quality degrades twice as fast
                @DisplayName("Quality decrease twice as fast: 4 => 2")
                @Test
                void nameNotModified() {
                    // Given
                    Item item = new Item("item", 0, 4);
                    GildedRose application = new GildedRose(new Item[]{item});

                    // When
                    application.updateQuality();

                    // Then
                    Item actual = application.items[0];
                    assertEquals(2, actual.quality);
                }

                // The Quality of an item is never negative
                @DisplayName("And quality is zero, is stays zero")
                @Test
                void qualityStaysZero() {
                    // Given
                    Item item = new Item("item", 0, 0);
                    GildedRose application = new GildedRose(new Item[]{item});

                    // When
                    application.updateQuality();

                    // Then
                    Item actual = application.items[0];
                    assertEquals(0, actual.quality);
                }


            }

        }

        @Nested
        @DisplayName("if is special")
        class Special {

            // "Aged Brie" actually increases in Quality the older it gets
            @Nested
            @DisplayName("it is an aged brie")
            class AgedBrie {

                @Nested
                @DisplayName("and is not expired")
                class NotExpired {

                    @DisplayName("quality increase by one : 4 => 6")
                    @Test
                    void nameNotModified() {
                        // Given
                        String AgedBrie = "Aged Brie";
                        Item item = new Item(AgedBrie, 10, 4);
                        GildedRose application = new GildedRose(new Item[]{item});

                        // When
                        application.updateQuality();

                        // Then
                        Item actual = application.items[0];
                        assertEquals(5, actual.quality);
                    }

                }

                @Nested
                @DisplayName("and is expired")
                class Expired {

                    @DisplayName("quality increase by two : 4 => 6")
                    @Test
                    void nameNotModified() {
                        // Given
                        String AgedBrie = "Aged Brie";
                        Item item = new Item(AgedBrie, 0, 4);
                        GildedRose application = new GildedRose(new Item[]{item});

                        // When
                        application.updateQuality();

                        // Then
                        Item actual = application.items[0];
                        assertEquals(6, actual.quality);
                    }

                }

            }

            @Nested
            @DisplayName("if there are several items")
            class SeveralItems {


                @DisplayName("they should all be updated")
                @Test
                void allUpdated() {
                    // Given
                    Item[] items = {
                        new Item("first-item", 0, 4),
                        new Item("second-item", 0, 2)
                    };
                    GildedRose application = new GildedRose(items);

                    // When
                    application.updateQuality();

                    // Then
                    int size = application.items.length;
                    assertEquals(2, size);

                    Item first = application.items[0];
                    assertEquals("first-item", first.name);
                    assertEquals(2, first.quality);

                    Item second = application.items[1];
                    assertEquals("second-item", second.name);
                    assertEquals(0, second.quality);
                }


            }
        }


    }
}
