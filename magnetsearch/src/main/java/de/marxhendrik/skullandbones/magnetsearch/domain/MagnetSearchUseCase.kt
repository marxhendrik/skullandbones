package de.marxhendrik.skullandbones.magnetsearch.domain

import de.marxhendrik.skullandbones.core.base.usecase.UseCase
import de.marxhendrik.skullandbones.magnetsearch.data.model.SearchResult
import de.marxhendrik.skullandbones.magnetsearch.data.repo.MagnetSearchRepo

class MagnetSearchUseCase(private val repo: MagnetSearchRepo) : UseCase<String, List<SearchResult>> {
    override suspend operator fun invoke(param: String) = repo.search(param)
}
