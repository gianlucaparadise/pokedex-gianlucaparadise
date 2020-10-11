package com.gianlucaparadise.pokedex.backend

import com.gianlucaparadise.pokedex.di.networkModule
import com.gianlucaparadise.pokedex.network.PokeApiService
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class BackendTest : KoinTest {

    private val service by inject<PokeApiService>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.ERROR)
        modules(networkModule)
    }

    @Test
    fun `test pokemon list limit`() {
        val limit = 2

        val pokemonList = runBlocking {
            service.getPokemonList(0, limit)
        }

        assertNotNull(pokemonList.results)
        assertEquals(pokemonList.results?.size, limit)
    }

    @Test
    fun `test pokemon list pagination`() {
        val limit = 10

        val page1 = runBlocking {
            service.getPokemonList(0, limit)
        }

        assertNotNull(page1.results)

        val lastFromPage1 = page1.results?.lastOrNull()
        assertNotNull(lastFromPage1)

        val page2 = runBlocking {
            service.getPokemonList(limit - 1, limit)
        }

        assertNotNull(page2.results)

        val firstFromPage2 = page2.results?.firstOrNull()
        assertNotNull(firstFromPage2)

        assertEquals(lastFromPage1, firstFromPage2)
    }

    @Test
    fun `test pokemon detail`() {
        val limit = 10

        val pokemonList = runBlocking {
            service.getPokemonList(0, limit)
        }

        val last = pokemonList.results?.lastOrNull()
        assertNotNull(last)
        assertNotNull(last?.name)

        val pokemonDetail = runBlocking {
            service.getPokemon(last?.name!!)
        }

        assertEquals(pokemonDetail.name, last?.name)
    }
}