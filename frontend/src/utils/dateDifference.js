const dateDifference = (data)=>{
    let start = Date.parse(data)
    let end = Date.now();
    start = new Date(start)
    end = new Date(end)
    
    let diffInTime = Math.abs(end - start)
    let timeInOneDay = 1000 * 60 * 60 *24
    let difDate = Math.ceil(diffInTime / timeInOneDay)
    return difDate
}

export default dateDifference