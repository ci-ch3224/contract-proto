export class Pageable {
  page = 0
  totalPages = 0
  size = 10
  pageSizes = [5, 10, 20, 50, 100]
  private _currentPage = 1

  constructor () {
    this.currentPage = 1
  }

  resetPage (): void {
    this.currentPage = 1
  }

  setDataPage (data: any): void {
    this.totalPages = data.totalPages
    this.currentPage = data.number + 1
  }

  set currentPage (value: number) {
    this._currentPage = value
    this.page = this._currentPage - 1
  }

  get currentPage () {
    return this._currentPage
  }
}
