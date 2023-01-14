function getAllLocations() {
    var list;
    $.ajax({
        url: "http://localhost:8081/location",
        method: "GET",
        crossDomain: true,
        async: false,
        success: function(data) {
            list = data;
        }
    });

    return list;
}


function getAllMunicipalities() {
    var list;
    $.ajax({
        url: "http://localhost:8081/municipality",
        method: "GET",
        crossDomain: true,
        async: false,
        success: function(data) {
            list = data;
        }
    });

    return list;
}

function getAllRoutes() {
    var list;
    $.ajax({
        url: "http://localhost:8081/route",
        method: "GET",
        crossDomain: true,
        async: false,
        success: function(data) {
            list = data;
        }
    });

    return list;
}

// --------------- Location Logic ---------------------

function DeleteLocation() {
    id = document.getElementById('deleteLocationModalButton').text;
    $.ajax({
        url: "http://localhost:8081/location/delete/" + id,
        method: "DELETE",
        crossDomain: true,
        success: function() {
            $('#deleteLocationModal').modal('hide');
            location.reload();

        }

    });
}

function EditLocation() {

    var cityName = document.getElementById('editLocationName').value;
    var cityId = document.getElementById('editLocationName').text;

    var municipalityId = parseInt($('#editLocationMunicipality').find(":selected").val());
    var municipalityName = $('#editLocationMunicipality').find(":selected").text();

    const mun = new MunicipalityEntity(municipalityId, municipalityName);
    const updateLocation = new LocationEntityWithId(cityId, cityName, mun);


    $.ajax({
        url: "http://localhost:8081/location/update",
        method: "PUT",
        crossDomain: true,
        contentType: "application/json",
        data: JSON.stringify(updateLocation),
        success: function() {

            location.reload();

        }
    });
}

function setDataInLocationEditModal(number) {

    const locationsList = getAllLocations();
    const currentLocation = locationsList.filter(function(item) {
        return item.id == number;
    })[0];

    console.log(currentLocation);
    $.ajax({
        url: "http://localhost:8081/municipality",
        method: "GET",
        async: false,
        success: function(data) {
            $('#editLocationMunicipality').empty();
            document.getElementById("editLocationForm").reset();

            $.each(data, function(i, option) {
                $('#editLocationMunicipality').append($('<option/>').attr("value", option.id).text(option.name));
            });
        },
        fail: function() {
            alert("Request failed.....");
        }
    });
    document.getElementById("editLocationName").value = currentLocation.name;
    document.getElementById("editLocationName").text = currentLocation.id;




}

function setDataInLocationDeleteModal(number) {
    document.getElementById('deleteLocationModalButton').text = number;
}




$('#addButtonModalLocation').on('click', function() {
    $.ajax({
        url: "http://localhost:8081/municipality",
        method: "GET",
        async: false,
        success: function(data) {
            $('#addLocationMunicipality').empty();
            document.getElementById("editLocationForm").reset();

            $.each(data, function(i, option) {
                $('#addLocationMunicipality').append($('<option/>').attr("value", option.id).text(option.name));
            });
        },
        fail: function() {
            alert("Request failed.....");
        }
    });
});

$('#addButtonLocation').on('click', function() {
    var cityName = document.getElementById('locationName').value;

    var municipalityId = $('#addLocationMunicipality').find(":selected").val();
    var municipalityName = $('#addLocationMunicipality').find(":selected").text();

    const mun = new MunicipalityEntity(municipalityId, municipalityName);
    const loc = new LocationEntity(cityName, mun);
    $.ajax({
        url: "http://localhost:8081/location/add",
        method: "POST",
        crossDomain: true,
        contentType: "application/json",
        data: JSON.stringify(loc),
        success: function() {
            $('#addLocationModal').modal('hide');
            location.reload();

        }
    });
});

// --------------- Municipality Logic ---------------------
$('#addButtonMunicipality').on('click', function() {
    var municipalityName = document.getElementById('municipalityName').value;

    $.ajax({
        url: "http://localhost:8081/municipality/add/" + municipalityName,
        method: "POST",
        crossDomain: true,
        success: function() {
            $('#addMunicipalityModal').modal('hide');
            location.reload();

        }
    });
});

function DeleteMunicipality() {
    id = document.getElementById('deleteMunicipalityModalButton').text;
    $.ajax({
        url: "http://localhost:8081/municipality/delete/" + id,
        method: "DELETE",
        crossDomain: true,
        success: function() {
            $('#deleteMunicipalityModal').modal('hide');
            location.reload();

        }

    });
}

function setDataInMunicipalityDeleteModal(number) {
    document.getElementById('deleteMunicipalityModalButton').text = number;
}

function setDataInMunicipalityEditModal(number) {

    const municipalitiesList = getAllMunicipalities();
    const currentMunicipality = municipalitiesList.filter(function(item) {
        return item.id == number;
    })[0];

    document.getElementById("editMunicipalityName").value = currentMunicipality.name;
    document.getElementById("editMunicipalityName").text = currentMunicipality.id;

}

function EditMunicipality() {

    var municipalityName = document.getElementById('editMunicipalityName').value;
    var municipalityId = document.getElementById('editMunicipalityName').text;

    const mun = new MunicipalityEntity(municipalityId, municipalityName);


    $.ajax({
        url: "http://localhost:8081/municipality/update",
        method: "PUT",
        crossDomain: true,
        contentType: "application/json",
        data: JSON.stringify(mun),
        success: function() {

            location.reload();

        }
    });
}


//-------------------- Routes -------------------------

$('#addButtonModalRoute').on('click', function() {
    $.ajax({
        url: "http://localhost:8081/location",
        method: "GET",
        async: false,
        success: function(data) {
            $('#addRouteStartingLocation').empty();
            $('#addRouteFinalLocation').empty();
            document.getElementById("editRouteForm").reset();

            $.each(data, function(i, option) {
                $('#addRouteStartingLocation').append($('<option/>').attr("value", option.id).text(option.name));
                $('#addRouteFinalLocation').append($('<option/>').attr("value", option.id).text(option.name));

            });
        },
        fail: function() {
            alert("Request failed.....");
        }
    });
});



$('#addButtonRoute').on('click', function() {
    var allLocations = getAllLocations();
    var departure = document.getElementById('RouteDeparture').value;

    var startingCityId = $('#addRouteStartingLocation').find(":selected").val();
    var finalCityId = $('#addRouteFinalLocation').find(":selected").val();

    var startingLocationEntity = allLocations.filter(function(item) {
        return item.id == startingCityId;
    })[0];
    var finalLocationEntity = allLocations.filter(function(item) {
        return item.id == finalCityId;
    })[0];

    var newRoute = new RouteEntity(startingLocationEntity, finalLocationEntity, departure);


    $.ajax({
        url: "http://localhost:8081/route/add",
        method: "POST",
        crossDomain: true,
        contentType: "application/json",
        data: JSON.stringify(newRoute),
        success: function() {
            $('#addRouteModal').modal('hide');
            location.reload();

        }
    });
});

function setDataInRouteDeleteModal(number) {
    document.getElementById('deleteRouteModalButton').text = number;
}

function DeleteRoute() {
    id = document.getElementById('deleteRouteModalButton').text;
    $.ajax({
        url: "http://localhost:8081/route/delete/" + id,
        method: "DELETE",
        crossDomain: true,
        success: function() {
            $('#deleteRouteModal').modal('hide');
            location.reload();

        }

    });
}
//-------------------- Search ------------------------

function resetTable() {
    $("#dataTable tbody tr").remove();
}

function fillOptions(result) {
    result.forEach(element => $('#dataTable').append('<tr><td>' + element.startingLocationEntity.name + '</td><td>' + element.finalLocationEntity.name + '</td><td>' + element.departure + '</td></tr>'))
};

function loadInitialTable(){
var routes = getAllRoutes();
fillOptions(routes);
}

function filterTable() {
    var final = document.getElementById('finalLocation').value;
    var starting = document.getElementById('startingLocation').value;
    var departure = document.getElementById('searchDeparture').value;

        var routerFilter = new RouteDTO(starting, final, departure);
    $.ajax({
        url: "http://localhost:8081/route/filter",
        method: "POST",
        crossDomain: true,
        async:false,
        contentType: "application/json",
        data: JSON.stringify(routerFilter),
        success: function(data) {
            resetTable();
            fillOptions(data);

        }
    });
}



// ------------------- Entities -----------------------


function LocationEntity(name, municipality) {
    this.name = name;
    this.municipality = municipality;
}

function LocationEntityWithId(id, name, municipality) {
    this.id = id;
    this.name = name;
    this.municipality = municipality;
}

function MunicipalityEntity(id, name) {
    this.id = id;
    this.name = name;
}

function RouteEntity(startingLocationEntity, finalLocationEntity, departure) {
    this.startingLocationEntity = startingLocationEntity;
    this.finalLocationEntity = finalLocationEntity;
    this.departure = departure;
}

function RouteDTO(start, final, departure) {
    this.start = start;
    this.final = final;
    this.departure = departure;
}
