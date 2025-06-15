function showcourse()
{

    fetch("http://localhost:8080/courses")      //getting the API endpoint ip
    .then((response) => response.json())        //getting response and converting to JSON format
    .then((course) =>{
        const dataTable = document.getElementById("cr");
        course.forEach(c => {
            
            var row = `<tr>
            <td>${c.cid}</td>
            <td>${c.cname}</td>
            <td>${c.trainer}</td>
            <td>${c.time}</td>
            </tr>`;

            dataTable.innerHTML+=row;

        });
    })


}