"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var pet_service_1 = require('../services/pet.service');
var AppComponent = (function () {
    function AppComponent(petService) {
        this.petService = petService;
    }
    AppComponent.prototype.getPets = function () {
        var _this = this;
        this.petService
            .getPets()
            .then(function (pets) { return _this.pets = pets; })
            .catch(function (error) { return _this.error = error; });
    };
    AppComponent.prototype.ngOnInit = function () {
        this.getPets();
    };
    AppComponent = __decorate([
        core_1.Component({
            selector: 'pets',
            //template: `<h1>hello</h1>`
            templateUrl: 'app/templates/pets.template.html',
        }), 
        __metadata('design:paramtypes', [pet_service_1.PetService])
    ], AppComponent);
    return AppComponent;
}());
exports.AppComponent = AppComponent;
//# sourceMappingURL=welcome.component.js.map