class localStorageService {

  ls = window.localStorage

  async setItem(key, value) {
    value = JSON.stringify(value)
    this.ls.setItem(key, value)
    return true
  }

  getItem(key) {
    let value = this.ls.getItem(key)
    try {
      return JSON.parse(value)
    } catch (e) {
      return null
    }
  }

  removeIteam(key){
    let value = this.ls.getItem(key)
    this.ls.removeItem(key);
    return true
  }

}

export default new localStorageService();